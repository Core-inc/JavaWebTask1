#!/bin/sh
VERSION="0.0.1-SNAPSHOT"
NEWJARS="management-app-service/target/management-app-service-$VERSION.jar:management-app-web/target/management-app-web-$VERSION.jar"

java -cp $CLASSPATH:$NEWJARS -jar management-app-main/target/management-app-main-$VERSION.war com.teamcore.manageapp.main.SiteApplication
