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
    apt install -y \
    lz4 \
    zstd
# Set locale to UTF-8
RUN echo "en_US.UTF-8 UTF-8" > /etc/locale.gen && \
    locale-gen en_US.UTF-8 && \
    update-locale LANG=en_US.UTF-8
# Set environment variables for locale
ENV LANG=en_US.UTF-8
ENV LANGUAGE=en_US:en
ENV LC_ALL=en_US.UTF-8

# create a user and add to sudo group
RUN useradd -ms /bin/bash yoctouser && \
echo "yoctouser ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers
# Switch to the new user
USER yoctouser  
# Set the working directory
RUN mkdir -p /opt/yocto
WORKDIR /opt/yocto
# Change ownership of /opt/yocto to the new user
RUN chown -R yoctouser:yoctouser /opt/yocto

# Copy the entire repo into the container
# this is done because we need native linux filesystem for proper build of yocto
COPY . /opt/yocto 
# Add /workspace to PATH
ENV PATH="/opt/yocto:${PATH}"
# Make sure scripts are in Unix format
RUN find /opt/yocto/ -type f -exec dos2unix {} \;
# Set the working directory to /opt/yocto
WORKDIR /opt/yocto  

# execute python3 clone_layers.py script to clone layers
# RUN python3 clone_layers.py

# run bitbake build
RUN ./bitbake_bb_rpi -h bb -d sysv -t custom-image

# Set default shell to bash
SHELL ["/bin/bash", "-c"]

# Default command: open a shell (so you can run any script or build interactively)
CMD ["/bin/bash"]