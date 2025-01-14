### Build
```
sbt compile
```

### Run
```
sbt run
```

### Rationale

Using Services or Microservices forces to split what would be a single databse.
The consequence is that you cannot join tables anymore (not at least in transactional service, you always can in big data).
Now how do "link" and connect data across different services with different domains and different data stores(Nosql, Relational, Graph, etc)?

### Some options

1. ID per product (package `services`)
2. True, Global and unique ID (package `global`)
3. Opaque ID (TBD)
4. Data duplication (TBD)

### ID per product

Every service contract define it's own ID:
* Login service: `loginId`
* Product service: `productId`
* Purchase service: `purchaseId`

No service know about each other, what if they need to call eatch other? How the correlate IDs that are not correlated?
There is the Accounts service that holds all product links between services.
This is basically a lookup service that holds the reference you know how link ids.

### Global

Here there is not IDs per domain (at least not exposde on the public contract) you might still have internal and 
private ids that do not get exposed on the contract. See here that NATURALLY things have links since they all use the same global ID.
Here for sake of INVENTORY we have the ids being hold by the AccountsService but we dont need id.
In fact the only thing we need is the account id generate a global id.

### Problems

Since this is a POC it's shows to show options and tradeoffs but also problems.
The main problem here is that there is no validation and no consistency checks, anyonce can pass any id and john == petter will be true.
Because we are not checking anything, for more on that check: https://github.com/diegopacheco/scala-playground/tree/master/scala-3-proof-of-9
