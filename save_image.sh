#!/bin/bash


echo "Show all the Images"

echo "------------------"

docker images

echo "Please enter Image name"

read IMAGE_NAME

echo "Please enter Tag"

read TAG

echo "Please enter new Image tar file name "

read NEW_IMAGE

#Save the docker Image

docker save "$IMAGE_NAME":"$TAG" > "$NEW_IMAGE".tar
