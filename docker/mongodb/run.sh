#!/bin/bash
set -e

# build Dockerfile
docker build -t pp-mongo .

# docker run
docker run -d -p 27017:27017 --restart=always --name pp-mongo pp-mongo

