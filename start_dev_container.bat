@echo off
REM filepath: d:\OneDrive\Documents\GitHub\ycoto-excersise\run_docker.bat

setlocal

REM Set image and container names
set IMAGE_NAME=yocto-dev
set CONTAINER_NAME=yocto-dev-native
set HOST_CODE_DIR=%CD%
set CONTAINER_CODE_DIR=/opt/yocto/

REM Check for "clean" argument
if /i "%1"=="clean" (
    echo Removing existing image %IMAGE_NAME%...
    docker rmi -f %IMAGE_NAME%
    echo Removing existing container %CONTAINER_NAME%...
    docker rm -f %CONTAINER_NAME% >nul 2>&1
    exit /b 0
)

REM Check if Docker is running
docker info >nul 2>&1
if errorlevel 1 (
    echo [ERROR] Docker Desktop is not running. Please start Docker Desktop and try again.
    pause
    exit /b 1
)

REM Build the Docker image if it does not exist
echo [INFO] Building image if not exists...
docker image inspect %IMAGE_NAME% >nul 2>&1
if errorlevel 1 (
    docker build -t %IMAGE_NAME% .
)

REM Remove existing container if it exists
docker ps -a --format "{{.Names}}" | findstr /i "^%CONTAINER_NAME%$" >nul
if not errorlevel 1 (
    echo [INFO] Removing existing container %CONTAINER_NAME%...
    docker rm -f %CONTAINER_NAME% >nul
)

REM Start the Docker container in detached mode
echo [INFO] Starting Docker container...
docker run -dit --name %CONTAINER_NAME% --hostname yocto %IMAGE_NAME% bash

REM Copy code into the container
echo [INFO] Copying code into container native path...
docker exec %CONTAINER_NAME% mkdir -p %CONTAINER_CODE_DIR%
docker cp "%HOST_CODE_DIR%\." %CONTAINER_NAME%:%CONTAINER_CODE_DIR%/

REM Optionally, launch VS Code attached to the container
echo [INFO] Launching VS Code...
code --folder-uri "vscode-remote://attached-container+%CONTAINER_NAME%%CONTAINER_CODE_DIR%"

endlocal
