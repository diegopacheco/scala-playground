### Build
```
sbt compile
```

### Run
```
sbt run
```

### Create the DBs
```bash
./run-postgres-db.sh
```

### Connect on the psql terminal
```bash
PGPASSWORD=pass psql -U postgres -h 127.0.0.1 -p 5433 
```

List all DBs
```sql
\list
```

switch to a db
```sql
\c person
```

list all tables
```sql
\dt
```

describe a table
```sql
\d person
```

### Run psql terminal

````bash
./run-psql-terminal.sh
````