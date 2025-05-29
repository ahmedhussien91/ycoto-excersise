@echo off
setlocal

set CONTAINER_NAME=yocto-dev-native
set HOST_CODE_DIR=%CD%
set CONTAINER_CODE_DIR=/opt/yocto/

echo [INFO] Copying code from container back to host...
docker cp %CONTAINER_NAME%:%CONTAINER_CODE_DIR%/. "%HOST_CODE_DIR%"

echo [INFO] Stopping and removing container...
docker stop %CONTAINER_NAME%
docker rm %CONTAINER_NAME%

echo [INFO] Done.

endlocal