#!/bin/bash

#
# 启动Quarusis参考
#

DIRNAME=$0
if [ "${DIRNAME:0:1}" = "/" ];then
    CURDIR=`dirname $DIRNAME`
else
    CURDIR="`pwd`"/"`dirname $DIRNAME`"
fi

# 输出$CURDIR的路径
# echo $CURDIR

# 开启MySQL、MongoDB服务

cd $CURDIR

# webpack打包
# npm run build

# run node,在后台执行(不回显在终端)
# npm run server &>/dev/null

# maven run,在pom.xml中已设置，package的时候run
# mvn clean package

