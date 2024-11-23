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

### Get the REST endpoint result from browser

```bash
sbt "runMain com.github.diegopacheco.slick.postgres.api.PekkoHttpApp"
```
```bash
http://localhost:8080/computers
```
```
// 20241122205750
// http://localhost:8080/computers

[
  {
    "jsonClass": "com.github.diegopacheco.slick.postgres.api.ComputerModel",
    "id": 1,
    "name": "MacBook Pro 15.4 inch",
    "manufacturerId": 1
  },
  {
    "jsonClass": "com.github.diegopacheco.slick.postgres.api.ComputerModel",
    "id": 2,
    "name": "CM-2a",
    "manufacturerId": 2
  },
  {
    "jsonClass": "com.github.diegopacheco.slick.postgres.api.ComputerModel",
    "id": 3,
    "name": "CM-200",
    "manufacturerId": 2
  },
  ...
```