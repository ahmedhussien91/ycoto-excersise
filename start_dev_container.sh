#!/bin/bash
# filepath: start_dev_container.sh

IMAGE_NAME=yocto-dev
CONTAINER_NAME=yocto-dev-native
HOST_CODE_DIR="$PWD"
CONTAINER_CODE_DIR=/opt/yocto/

if [[ "$1" == "clean" ]]; then
    echo "Removing existing image $IMAGE_NAME..."
    docker rmi -f "$IMAGE_NAME"
    echo "Removing existing container $CONTAINER_NAME..."
    docker rm -f "$CONTAINER_NAME" 2>/dev/null
    exit 0
fi

# Check if Docker is running
if ! docker info >/dev/null 2>&1; then
    echo "[ERROR] Docker is not running. Please start Docker and try again."
    exit 1
fi

# Build the Docker image if it does not exist
if ! docker image inspect "$IMAGE_NAME" >/dev/null 2>&1; then
    docker build -t "$IMAGE_NAME" .
fi

# Remove existing container if it exists
if docker ps -a --format '{{.Names}}' | grep -wq "$CONTAINER_NAME"; then
    echo "[INFO] Removing existing container $CONTAINER_NAME..."
    docker rm -f "$CONTAINER_NAME"
fi

# Start the Docker container in detached mode
echo "[INFO] Starting Docker container..."
docker run -dit --name "$CONTAINER_NAME" --hostname yocto "$IMAGE_NAME" bash

# Copy code into the container
echo "[INFO] Copying code into container native path..."
docker exec "$CONTAINER_NAME" mkdir -p "$CONTAINER_CODE_DIR"
docker cp "$HOST_CODE_DIR/." "$CONTAINER_NAME":"$CONTAINER_CODE_DIR"

# Optionally, launch VS Code attached to the container
if command -v code >/dev/null 2>&1; then
    echo "[INFO] Launching VS Code..."
    code --folder-uri "vscode-remote://attached-container+$CONTAINER_NAME$CONTAINER_CODE_DIR"
fi