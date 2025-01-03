#!/bin/bash

set -e

JAR_FILE="hotdog-saas-1.0.0.jar"

# 堆内存
JAVA_OPTS="-Xms512m -Xmx512m"
# logging
JAVA_OPTS="${JAVA_OPTS} -Dlogging.path=/data/logs"

JAVA_OPTS="${JAVA_OPTS} -XX:+UseG1GC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -Xloggc:/data/logs/gc.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=3 -XX:GCLogFileSize=2M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data/logs/app.bin"

java ${JAVA_OPTS} -jar /app/${JAR_FILE}
