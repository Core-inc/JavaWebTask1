#!/bin/sh
DB_FOLDER="../management-app-service/src/main/resources/db/"
psql -U postgres -d java_lab -f $DB_FOLDER/cleanup.sql

