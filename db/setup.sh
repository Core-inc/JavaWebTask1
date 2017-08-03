#!/bin/sh
DB_FOLDER="../src/main/resources/db/"
psql -U team_core -d webtask1 -f $DB_FOLDER/cleanup.sql
psql -U team_core -d webtask1 -f $DB_FOLDER/init_schema.sql
psql -U team_core -d webtask1 -f $DB_FOLDER/init_data.sql

