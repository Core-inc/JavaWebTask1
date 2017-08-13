#!/bin/sh

if [ $# -lt 1 ]; then
	echo "$0 [test_pattern]"
	exit -1
fi

PATTERN=$1

mvn compile
mvn install -pl management-app-service -Dmaven.test.skip=true
mvn test -pl management-app-web -Dtest=$PATTERN

