#!/bin/bash
# filepath: stop_dev_container.sh

CONTAINER_NAME=yocto-dev-native
HOST_CODE_DIR="$PWD"
CONTAINER_CODE_DIR=/opt/yocto/

echo "[INFO] Copying code from container back to host..."
docker cp "$CONTAINER_NAME":"$CONTAINER_CODE_DIR/." "$HOST_CODE_DIR"

echo "[INFO] Stopping and removing container..."
docker stop "$CONTAINER_NAME"
docker rm "$CONTAINER_NAME"

echo "[INFO] Done."