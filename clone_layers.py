#!/usr/bin/env python3

import os
import subprocess

# Configuration
BRANCH = "scarthgap"
REPOS = [
    ("git://git.yoctoproject.org/meta-arm", "meta-arm"),
    ("git://git.openembedded.org/meta-openembedded", "meta-openembedded"),
    ("https://github.com/meta-qt5/meta-qt5.git", "meta-qt5"),
    ("git://git.yoctoproject.org/meta-raspberrypi", "meta-raspberrypi"),
    ("git://git.yoctoproject.org/meta-ti", "meta-ti"),
    ("git://git.yoctoproject.org/meta-virtualization", "meta-virtualization"),
    ("git://git.yoctoproject.org/poky", "poky"),
]

CONF_PATCHES = [
    ("rpi-build/conf/local.conf", "s|\\$REPO_DIR|{repo_dir}|g"),
    ("rpi-build/conf/bblayers.conf", "s|\\$REPO_DIR|{layers_dir}|g"),
    ("bb-build/conf/local.conf", "s|\\$REPO_DIR|{repo_dir}|g"),
    ("bb-build/conf/bblayers.conf", "s|\\$REPO_DIR|{layers_dir}|g"),
    ("bb-build-sysv/conf/local.conf", "s|\\$REPO_DIR|{repo_dir}|g"),
    ("bb-build-sysv/conf/bblayers.conf", "s|\\$REPO_DIR|{layers_dir}|g"),
]

def run(cmd, cwd=None):
    print(f"Running: {' '.join(cmd)}")
    subprocess.check_call(cmd, cwd=cwd)

def clone_and_checkout(repo_url, dirname, branch):
    if not os.path.exists(dirname):
        run(["git", "clone", repo_url, dirname])
    else:
        print(f"Directory {dirname} already exists, skipping clone.")
    run(["git", "fetch"], cwd=dirname)
    run(["git", "checkout", branch], cwd=dirname)

def patch_conf_files(repo_dir, layers_dir):
    for conf_file, sed_expr in CONF_PATCHES:
        if os.path.exists(conf_file):
            expr = sed_expr.format(repo_dir=repo_dir, layers_dir=layers_dir)
            print(f"Patching {conf_file} with: sed -i {expr} {conf_file}")
            # Read original lines for comparison
            with open(conf_file, "r", encoding="utf-8") as f:
                original_lines = f.readlines()
            run(["sed", "-i", expr, conf_file])
            # Read new lines and print changes
            with open(conf_file, "r", encoding="utf-8") as f:
                new_lines = f.readlines()
            for i, (orig, new) in enumerate(zip(original_lines, new_lines), 1):
                if orig != new:
                    print(f"Line {i} changed:\n- {orig.rstrip()}\n+ {new.rstrip()}")
            # If file got longer, print added lines
            if len(new_lines) > len(original_lines):
                for i in range(len(original_lines), len(new_lines)):
                    print(f"Line {i+1} added:\n+ {new_lines[i].rstrip()}")
        else:
            print(f"Warning: {conf_file} does not exist, skipping.")

def main():
    current_dir = os.getcwd()
    repo_dir = os.path.abspath(os.path.join(current_dir, ".."))
    layers_dir = current_dir

    # Clone and checkout all repos
    for url, dirname in REPOS:
        clone_and_checkout(url, dirname, BRANCH)

    # Patch configuration files
    patch_conf_files(repo_dir, layers_dir)

if __name__ == "__main__":
    main()
