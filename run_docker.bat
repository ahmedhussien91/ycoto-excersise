@echo off
REM filepath: run_docker.bat

REM Set image name
set IMAGE_NAME=yocto-dev

REM Check for "clean" argument
if /i "%1"=="clean" (
    echo Removing existing image %IMAGE_NAME%...
    docker rmi -f %IMAGE_NAME%
)

REM Build the Docker image
docker build -f Dockerfile -t %IMAGE_NAME% .

REM Run the container interactively, mounting the current directory
docker run --rm -it -v "%cd%:/opt/yocto" %IMAGE_NAME%