### Build
```
sbt compile
```

### Run
```
sbt run
```

### Rationale

Using Services or Microservices forces to split what would be a single database.
The consequence is that you cannot join tables anymore (not at least in transactional service, you always can in big data).
Now how do "link" and connect data across different services with different domains and different data stores(Nosql, Relational, Graph, etc)?
</br>
</br>
Consider an E-commerce system with the following services:
* Login: Where the use will authenticate and use to log in on the website.
* Email: Where the user will receive emails, send mkt notifications and campaign for selling products etc...
* Purchase: Where the user will buy products. Keeps track of the user purchases. Maybe to drive more sales.
* Account: Where the user account is stored. It's a global service that holds links between services.

There are `74 tests` all passing. Check them out!

### Some options

1. ID per product (package `services`)
2. True, Global and unique ID (package `global`)
3. Envelope: raw or sealed
4. Data duplication

### ID per product

Every service contract define its own ID:
* Login service: `loginId`
* Product service: `productId`
* Purchase service: `purchaseId`

Here I'm calling service, but it could also be: product_id, ddd_id, any other name that represent that thing. 

No service know about each other(meaning they are not a distributed monolith, so no database sharing), what if they need to call each other? How the correlate IDs that are not correlated?
There is the Accounts service that holds all product links between services.
This is basically a lookup service that holds the reference you know how link ids.
Such service could in a cache like redis(service provide more abstraction and isolation).

Benefits:
1. It's very simple
2. Not hard to implement, can be done incrementally

Downsides:
1. It's not scalable, if you have 100 services you will have 100 lookups. Of course, you could get ids in batch.
2. AccountsService cannot be down, otherwise nobody can do join anymore, of course we could have caches.

### Global

Here there is no IDs per domain (at least not explode on the public contract) you might still have internal and 
private ids that do not get exposed on the contract. See here that NATURALLY things have links since they all use the same global ID.
Here for sake of INVENTORY we have the ids being hold by the AccountsService, but we don't need id.
In fact the only thing we need is the account id generate a global id. What we need to be careful in this approach is to make sure, in-fact there is just one id.

Benefits:
1. It's simple
2. It will scale
3. No joins necessary, since you know for the same user will be the same id always.
4. No need for a lookup service, no extra calls. No need for caches(same id always).

Downsides:
1. It's not easy to implement, you need to make sure you have a single id for the same user.
2. What if someone/system mess john and petter ids? you must have a way to cross-check.

### Envelope

Buckle up, this idea is more complex. It's like a TCP self-routing package or like JWT. Basically, there will be an envelope with is an object that can hold service->id links.
Such envelope could be raw(plaintext) or sealed(cyphertext). There is an API that can can open and seal the envelope,
the layout is something like this:
```
raw (plaintext) : service1:id1,service2:id2
sealed (sealed) : Qv/tidy3+882j26ec8CvRJAhchi+ooklm1fmTP8KZPc= 
```
Keep in mind, this is a poc(complex one already) so I did not go as crazy as I could go. 
But a real impl for an envelope would have at minimum version, header and more attributes, like last date of change etc...

Benefits:
1. It's self-contained, all the ids links are there.
2. It's secure, since is encrypted, we could have PII there. 
3. It's flexible, so we could evolve to envelope v2, v3, add more fields, rotate the keys under the hood (ofcourse would require a more complex envelope and layout)
4. IT's distributed and secure, anyone who has the key, can get all IDS associated with PII which can be used for cross-checking and for validations. NO REMOTE CALL REQUIRED. AIM permissions can be used or security groups to control access.
5. Since there are types/objects `Envelope` and `SealedEnvelope` all sorts of cross-checking and validation can be there.

Downsides:
1. It's complex (a lot to be honest)
2. It's a very big key (2 links == 44 bytes, UUID v4 is 36). More ids, bigger the ct size. Could be problem for relational dbs. We can offload of some of this issue by referring to the UUID only and doing a lookup on an internal table, them will always be 36 bytes always.
3. You cannot do joins on the envelope it-self, since is enc and once you rotate keys it's over for joins.
4. You could just USE JWT(but will be bigger, less secure IMHO). 

IF you look the code, there is always reference to a type `AccountID`:
```scala
trait AccountID {
  def getUUID: UUID
  def getEnvelope: Envelope
}
```
This is the link between Email, Login and Purchase for our E-Commerce website. The main benefit of forcing interations via a type is that we can enforce behavior, the bad new its that will introduce binary coupling(now we need a lib with Accounts/Envelope) and everybody need to have the lib everywhere for all languages and all services.
Such ID (UUID) it could be inside the envelope itself. 

### Data duplication

There are several different ways we can do this approach. Basically, we will use a shared bus, it could be Kafka(ideally), RabitMQ, SQS, SNS, nsq, etc.
For the POC I just did in memory, like the Observer Pattern in Java. The idea here is like CQRS and NoSQL Design, we will replicate(diplicate) a lot of data.
Basically we will publish events, every time some useful happens, them we will have a listener that will update the data in the other services.
Doing such approach allow us to share DATA and IDs if we want(I would just share data no IDS IF POSSIBLE) and with that we can do the things we need.

There are a couple of variations like:
1. Share all data (no ids)
2. Share all ids and links

This approaches allow us to replicate many tables in each service, so they can have all the information required. This pattern is Async. It works with distributed systems very well.

Benefits:
1. It's scalable
2. It's async and very efficient (reduce latency of RPC calls - less direct calls)
3. It's very flexible, you can share all data or just ids or just links.

Downsides:
1. It's complex, you need to have a good event bus and a good listener. Bus cannot lose data, retention could be a problem.
2. It's async, so you need to be careful with consistency, you might need to have a way to rollback or retry or even acklodge that you spread bug data (fake news of CQRS :-) ).
3. It's eventual consistent, meaning, what if one or more systems don't update they facts? we will have trouble.
4. Error handling can be harder and troubleshooting as well, because now your database change is unpredictable and could happen anytime, ofcource you have code to handle that.  

### Problems

Since this is a POC it's shows to show options and tradeoffs but also problems.
The main problem here is that there is no validation and no consistency checks, anyonce can pass any id and john == petter will be true.
Because we are not checking anything, for more on that check: https://github.com/diegopacheco/scala-playground/tree/master/scala-3-proof-of-9
