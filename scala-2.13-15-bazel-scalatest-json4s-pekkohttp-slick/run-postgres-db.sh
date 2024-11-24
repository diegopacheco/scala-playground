#!/bin/bash

POSTGRES_PASSWORD=pass

echo "***"
echo "*** Creating Person DB ***"
echo "***"

docker run --rm -d -e POSTGRES_PASSWORD=$POSTGRES_PASSWORD -p 5433:5432 --name postgres_test postgres:latest
echo "Connect to postgres client: "
echo "psql -U postgres -h 127.0.0.1 -p 5433"

sleep 10
echo "Creating person db... "
echo "CREATE DATABASE person;" | PGPASSWORD=$POSTGRES_PASSWORD psql -U postgres -h 127.0.0.1 -p 5433

sleep 3
echo "Creating person table... "
echo "CREATE TABLE IF NOT EXISTS person (
          id SERIAL PRIMARY KEY,
          first_name VARCHAR(255) NOT NULL,
          last_name VARCHAR(255) NOT NULL,
          version INT NOT NULL DEFAULT 0,
          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
      );" | PGPASSWORD=$POSTGRES_PASSWORD psql -U postgres -h 127.0.0.1 -p 5433 -d person

echo "Inserting data into person table... "
echo "INSERT INTO person (first_name, last_name) VALUES ('John', 'Doe');" | PGPASSWORD=$POSTGRES_PASSWORD psql -U postgres -h 127.0.0.1 -p 5433 -d person

echo "SELECT * FROM person;" | PGPASSWORD=$POSTGRES_PASSWORD psql -U postgres -h 127.0.0.1 -p 5433 -d person
echo "DONE."

echo "***"
echo "*** Creating Computer DB ***"
echo "***"

echo "Creating computer db... "
echo "CREATE DATABASE computers;" | PGPASSWORD=$POSTGRES_PASSWORD psql -U postgres -h 127.0.0.1 -p 5433

# execute the sql using src/main/sql/create.sql name the db computers
echo "Executing src/main/sql/create.sql... "
cat src/main/sql/create.sql | PGPASSWORD=$POSTGRES_PASSWORD psql -U postgres -h 127.0.0.1 -p 5433 -d computers
echo "Executing src/main/sql/create.sql... DONE"

echo "Query the db... "
echo "SELECT * FROM COMPANIES;" | PGPASSWORD=$POSTGRES_PASSWORD psql -U postgres -h 127.0.0.1 -p 5433 -d computers
echo "SELECT * FROM COMPUTERS;" | PGPASSWORD=$POSTGRES_PASSWORD psql -U postgres -h 127.0.0.1 -p 5433 -d computers

echo "DONE."