#!/bin/sh
DB_FOLDER="../management-app-service/src/main/resources/db/"
psql -U team_core -d webtask1 -f $DB_FOLDER/cleanup.sql

