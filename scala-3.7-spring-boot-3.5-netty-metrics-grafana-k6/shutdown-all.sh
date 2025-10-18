#!/bin/bash
CLUSTER_NAME="scala-app-cluster"
echo "Stopping and destroying Kind cluster ${CLUSTER_NAME}..."
if kind get clusters | grep -q "^${CLUSTER_NAME}$"; then
  kind delete cluster --name ${CLUSTER_NAME}
  echo "Cluster ${CLUSTER_NAME} destroyed successfully!"
else
  echo "Cluster ${CLUSTER_NAME} does not exist."
fi
