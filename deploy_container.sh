#!/bin/bash

# Specify the Image name and tag

echo "Show all the Images"

echo "------------------"

docker images

echo "Show all Networks"

echo "---------------------------"

docker network ls

echo "Please enter Image name"

read IMAGE_NAME

echo "Please enter Container name"

read CONTAINER_NAME

echo "Please enter Port"

read PORT

echo "PLease enter Network name"
read NETWORK

#Run the Docker Container

docker run --network "$NETWORK" --name "$CONTAINER_NAME" -p "$PORT" "$IMAGE_NAME"

