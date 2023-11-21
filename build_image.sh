#!/bin/bash

# Specify the name and tag for the Docker Image

echo "Please Enter Image name"

read IMAGE_NAME

TAG="latest"

#BUILD the Docker Image

docker build -t "$IMAGE_NAME:$TAG" .

echo "---------------------------"

echo "Check all the Images"

echo "---------------------------"

docker images


