#!/bin/bash

echo "*********************"
echo "Available DBs:"
echo "\c vending"
echo "Commands:"
echo "\l     -> list all dbs"
echo "\c db  -> connect to a db"
echo "\dt    -> list all tables in the current db"
echo "\d tbl -> describe a table"
echo "*********************"

PGPASSWORD=pass psql -U postgres -h 127.0.0.1 -p 5433