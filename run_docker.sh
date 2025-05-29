#!/bin/bash
# filepath: run_docker.sh

IMAGE_NAME=yocto-dev

if [[ "$1" == "clean" ]]; then
    echo "Removing existing image $IMAGE_NAME..."
    docker rmi -f "$IMAGE_NAME"
    exit 0
fi

# Build the Docker image
docker build -f Dockerfile -t "$IMAGE_NAME" .

# Run the container interactively, mounting the current directory
docker run --rm -it -v "$PWD:/opt/yocto" "$IMAGE_NAME"