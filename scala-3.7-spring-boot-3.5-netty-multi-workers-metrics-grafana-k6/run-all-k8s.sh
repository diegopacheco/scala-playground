#!/bin/bash
set -e
CLUSTER_NAME="scala-app-cluster"
echo "Building Scala applications..."
sbt clean
sbt "project fastEndpoints" assembly
sbt "project slowEndpoints" assembly
sbt "project healthChecker" assembly
echo "Building Docker images..."
podman build -t localhost/fast-endpoints:latest -f Dockerfile.fast .
podman build -t localhost/slow-endpoints:latest -f Dockerfile.slow .
podman build -t localhost/health-checker:latest -f Dockerfile.health .
echo "Saving images to tar files..."
podman save -o /tmp/fast-endpoints-image.tar localhost/fast-endpoints:latest
podman save -o /tmp/slow-endpoints-image.tar localhost/slow-endpoints:latest
podman save -o /tmp/health-checker-image.tar localhost/health-checker:latest
echo "Creating Kind cluster..."
if kind get clusters | grep -q "^${CLUSTER_NAME}$"; then
  echo "Cluster ${CLUSTER_NAME} already exists, deleting it..."
  kind delete cluster --name ${CLUSTER_NAME}
fi
kind create cluster --name ${CLUSTER_NAME} --config - <<EOF
kind: Cluster
apiVersion: kind.x-k8s.io/v1alpha4
nodes:
- role: control-plane
  extraPortMappings:
  - containerPort: 30081
    hostPort: 30081
    protocol: TCP
  - containerPort: 30082
    hostPort: 30082
    protocol: TCP
  - containerPort: 30083
    hostPort: 30083
    protocol: TCP
  - containerPort: 30090
    hostPort: 30090
    protocol: TCP
  - containerPort: 30300
    hostPort: 30300
    protocol: TCP
EOF
echo "Loading Docker images into Kind cluster..."
kind load image-archive /tmp/fast-endpoints-image.tar --name ${CLUSTER_NAME}
kind load image-archive /tmp/slow-endpoints-image.tar --name ${CLUSTER_NAME}
kind load image-archive /tmp/health-checker-image.tar --name ${CLUSTER_NAME}
rm -f /tmp/fast-endpoints-image.tar /tmp/slow-endpoints-image.tar /tmp/health-checker-image.tar
echo "Applying Kubernetes specs..."
kubectl apply -f specs/postgres-configmap.yaml
kubectl apply -f specs/postgres-deployment.yaml
kubectl apply -f specs/prometheus-rbac.yaml
kubectl apply -f specs/prometheus-configmap.yaml
kubectl apply -f specs/prometheus-deployment.yaml
kubectl apply -f specs/grafana-configmap.yaml
kubectl apply -f specs/grafana-deployment.yaml
echo "Waiting for PostgreSQL to be ready..."
until kubectl get pods -l app=postgres 2>/dev/null | grep -q Running; do
  sleep 1
done
kubectl wait --for=condition=ready pod -l app=postgres --timeout=120s
echo "Applying application deployments..."
kubectl apply -f specs/fast-endpoints-deployment.yaml
kubectl apply -f specs/slow-endpoints-deployment.yaml
kubectl apply -f specs/health-checker-deployment.yaml
echo "Waiting for applications to be ready..."
kubectl wait --for=condition=ready pod -l app=fast-endpoints --timeout=180s
kubectl wait --for=condition=ready pod -l app=slow-endpoints --timeout=180s
kubectl wait --for=condition=ready pod -l app=health-checker --timeout=180s
echo "Waiting for Prometheus to be ready..."
kubectl wait --for=condition=ready pod -l app=prometheus --timeout=120s
echo "Waiting for Grafana to be ready..."
kubectl wait --for=condition=ready pod -l app=grafana --timeout=120s
echo "All services are up and running!"
echo "Fast Endpoints: http://localhost:30081"
echo "Slow Endpoints: http://localhost:30082"
echo "Health Checker: http://localhost:30083"
echo "Grafana: http://localhost:30300 (admin/admin)"
echo "Prometheus: http://localhost:30090"
echo "Kubernetes Dashboard:"
kubectl get pods
kubectl get services
