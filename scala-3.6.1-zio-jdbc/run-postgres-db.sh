#!/bin/bash

POSTGRES_PASSWORD=pass

echo "***"
echo "*** Creating vending DB ***"
echo "***"

docker run --rm -d -e POSTGRES_PASSWORD=$POSTGRES_PASSWORD -p 5433:5432 --name postgres_test postgres:latest
echo "Connect to postgres client: "
echo "psql -U postgres -h 127.0.0.1 -p 5433"

sleep 10
echo "Creating vending db... "
echo "CREATE DATABASE vending;" | PGPASSWORD=$POSTGRES_PASSWORD psql -U postgres -h 127.0.0.1 -p 5433

# execute the sql using src/main/sql/create.sql name the db vending
echo "Executing src/main/sql/create.sql... "
cat src/main/sql/create.sql | PGPASSWORD=$POSTGRES_PASSWORD psql -U postgres -h 127.0.0.1 -p 5433 -d vending
echo "Executing src/main/sql/create.sql... DONE"

echo "DONE."