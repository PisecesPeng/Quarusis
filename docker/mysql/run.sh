#!/bin/bash
set -e

# build Dockerfile
docker build -t pp-mysql .

# docker run
docker run -d -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 --restart=always --name pp-mysql pp-mysql

# mysql.sql
mysql -uroot -proot -h172.17.0.1 < ./mysql.sql
