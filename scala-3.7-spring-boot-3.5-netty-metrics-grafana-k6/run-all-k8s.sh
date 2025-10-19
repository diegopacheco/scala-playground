#!/bin/bash
set -e
CLUSTER_NAME="scala-app-cluster"
echo "Building Scala application..."
sbt clean assembly
echo "Building Docker image..."
podman build -t scala-app:latest .
echo "Saving image to tar file..."
podman save -o /tmp/scala-app-image.tar scala-app:latest
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
  - containerPort: 30090
    hostPort: 30090
    protocol: TCP
  - containerPort: 30300
    hostPort: 30300
    protocol: TCP
EOF
echo "Loading Docker image into Kind cluster..."
kind load image-archive /tmp/scala-app-image.tar --name ${CLUSTER_NAME}
rm -f /tmp/scala-app-image.tar
echo "Applying Kubernetes specs..."
kubectl apply -f specs/postgres-configmap.yaml
kubectl apply -f specs/postgres-deployment.yaml
kubectl apply -f specs/app-configmap.yaml
kubectl apply -f specs/prometheus-configmap.yaml
kubectl apply -f specs/prometheus-deployment.yaml
kubectl apply -f specs/grafana-configmap.yaml
kubectl apply -f specs/grafana-deployment.yaml
echo "Waiting for PostgreSQL to be ready..."
kubectl wait --for=condition=ready pod -l app=postgres --timeout=120s
echo "Applying application deployment..."
kubectl apply -f specs/app-deployment.yaml
echo "Waiting for application to be ready..."
kubectl wait --for=condition=ready pod -l app=scala-app --timeout=180s
echo "Waiting for Prometheus to be ready..."
kubectl wait --for=condition=ready pod -l app=prometheus --timeout=120s
echo "Waiting for Grafana to be ready..."
kubectl wait --for=condition=ready pod -l app=grafana --timeout=120s
echo "All services are up and running!"
echo "Application: http://localhost:30081"
echo "Grafana: http://localhost:30300 (admin/admin)"
echo "Prometheus: http://localhost:30090"
echo "Kubernetes Dashboard:"
kubectl get pods
kubectl get services
