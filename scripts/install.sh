#!/bin/bash

NAMESPACE=$1
OS_PATH=./order-service
OS_IMAGE=szalaigeri/order-service:1.0.0
WH_PATH=./warehouse
WH_IMAGE=szalaigeri/warehouse:1.0.0

function mvnCleanInstall(){
  cd ${OS_PATH} && mvn clean install -DskipTests && cd ..
  cd ${WH_PATH} && mvn clean install -DskipTests && cd ..
}

function dockerBuildPush(){
  echo "Building order-service..."
  docker build ${OS_PATH} -t ${OS_IMAGE}
  docker push ${OS_IMAGE}
  echo "Building warehouse..."
  docker build ${WH_PATH} -t ${WH_IMAGE}
  docker push ${WH_IMAGE}
}

function cleanup(){
  echo "Deleting namespace ${NAMESPACE}..."
  kubectl delete ns "${NAMESPACE}"
  kubectl create ns "${NAMESPACE}"
}

function deployHelmChart(){
  echo "Deploying HELM chart in namespace ${NAMESPACE}..."

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