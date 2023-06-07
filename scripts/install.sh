#!/bin/bash

NAMESPACE=$1

function cleanup(){
  echo "Deleting namespace ${NAMESPACE}..."
  kubectl delete ns "${NAMESPACE}"
  kubectl create ns "${NAMESPACE}"
}

function mvnCleanInstall(){
  cd ./order-service && mvn clean install -DskipTests && cd ..
  cd ./warehouse && mvn clean install -DskipTests && cd ..
}

function dockerBuildPush(){
  echo "Building warehouse..."
  docker build ./warehouse -t szalaigeri/warehouse:1.0.0
  docker push szalaigeri/warehouse:1.0.0
  echo "Building order-service..."
  docker build ./order-service -t szalaigeri/order-service:1.0.0
  docker push szalaigeri/order-service:1.0.0
}

function deployHelmChart(){
  echo "Deploying HELM chart..."

  cd ./helm/restaurant/
  helm install restaurant . -f values.yaml -n "${NAMESPACE}"
}

read -p "Build docker images? (Yy): " -r input
if [[ "$input" =~ [Yy]$ ]]
then
    mvnCleanInstall
    dockerBuildPush
fi

cleanup
deployHelmChart