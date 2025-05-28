FROM ubuntu:20.04

ENV DEBIAN_FRONTEND=noninteractive
ENV LANG=C.UTF-8 LC_ALL=C.UTF-8

# Install Yocto dependencies
RUN apt-get update && apt-get install -y \
    build-essential \
    git \
    wget \
    python3 \
    python3-pip \
    python3-pexpect \
    diffstat \
    unzip \
    texinfo \
    gcc \
    g++ \
    make \
    cpio \
    python3-git \
    python3-pyparsing \
    python3-requests \
    python3-setuptools \
    chrpath \
    socat \
    libsdl1.2-dev \
    xterm \
    gawk \
    locales \
    sudo \
    dos2unix \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

RUN apt-get update && \
    apt-get install -y locales && \
    locale-gen en_US.UTF-8 && \
    update-locale LANG=en_US.UTF-8 && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

ENV LANG=en_US.UTF-8
ENV LANGUAGE=en_US:en
ENV LC_ALL=en_US.UTF-8

# Set the working directory
WORKDIR /opt/yocto

# Copy the entire repo into the container
COPY . /opt/yocto

# Make all scripts in scripts/ executable
RUN chmod +x /opt/yocto/scripts/*.sh || true
# Add /workspace to PATH
ENV PATH="/opt/yocto:${PATH}"
# Make sure scripts are in Unix format
RUN find /opt/yocto/ -type f -exec dos2unix {} \;

# Set default shell to bash
SHELL ["/bin/bash", "-c"]

# Default command: open a shell (so you can run any script or build interactively)
CMD ["/bin/bash"]