#!/bin/sh
mvn package -Dmaven.test.skip=true

VERSION="0.0.1-SNAPSHOT"
java -jar management-app-main/target/management-app-main-$VERSION.war com.teamcore.manageapp.main.SiteApplication
