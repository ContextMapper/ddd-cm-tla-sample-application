---
status: "accepted"
date: "2023-07-26"
deciders: "stefan-ka"
---
# AD1: Layering Scheme - Onion Architecture

## Context and Problem Statement

For the implementation of our sample application we use logical layers as primary decomposition paradigm. This ADR
documents for which layering scheme we decided for this application.

## Considered Options

* Three layers according to PoEAA[^1]
  * _presentation_, _domain_, _data source_ (order: top -> down)
* Onion architecture[^2]
  * _infrastructure_[^3], _application_, _domain_ (order: outside -> inside)
  * Side note: We use the term _ring(s)_ instead of _layer(s)_ here, because of the circular shape of the onion.

## Decision Outcome

Our application serves as an example to teach DDD and/with Context Mapper, which means, our main focus will be on the
implementation of the domain. For that reason, we attach particular importance to _a) keep the domain free from technology_
(a design principle that is often recommended; "separation of concerns") as well as _b) having a domain 
implementation that is independent of other layers containing technology_.

While both considered options (3-layer vs. onion) are legitimate solutions, we have gained positive experience in practical 
projects applying DDD by using Onion Architecture, particularly if _b)_ is given high priority. With the three layers 
according to PoEAA[^1] we can achieve _a)_ but not _b)_ (because the _domain_ layer depends on the _data source_ layer), 
while we can achieve both _a)_ and _b)_ with the Onion approach[^2].

Additionally, we expect different change rhythms in the domain and technology. The TLA domain is stable; there is no 
reason to expect that it will change drastically over the years. On the technology-side on the other hand, we may change
stuff to illustrate applications with other frameworks, API technologies, etc. A clear separation of domain and 
technology makes sense from this perspective as well.

For these reasons we decided to use the Onion Architecture as a layering scheme.

### Consequences

* We talk about _rings_ and not _layers_.
* We implement the Onion Architecture[^2] with the following rings:
  * infrastructure[^3]
  * application (containing the _application services_)
  * domain (optionally split into _domain services_ and _domain_, in case _domain services_ are needed)
* PoEAA layers[^1] might be better known by developers that haven't implemented DDD so far. That means we have to take 
  care that we explain Onion Architecture good enough in our workshops and teaching sessions so that people do 
  understand the code.

[^1]: PoEAA: Patterns of Enterprise Application Architecture, Martin Fowler, [Book](https://www.amazon.com/Patterns-Enterprise-Application-Architecture-Martin/dp/0321127420).
[^2]: [Onion Architecture](https://herbertograca.com/2017/09/21/onion-architecture/)
[^3]: We point out and acknowledge that the name _infrastructure_ is not ideal, because _infra_ comes from Latin and 
      means "below". This indicates that this layer or ring should be at the bottom of a layered architecture
      (or inside, if visualized circular as with Onion Architecture), but the opposite is the case. Robert C. Martin calls
      the outermost ring in his Clean Architecture "Frameworks and Drivers". In the Hexagonal Architecture one talks
      about "Adapters". Both names we would prefer over "Infrastructure". However, we decided to stick to that name for 
      now, as it is used by practitioners of the Onion Architecture layering scheme, and we would confuse people already 
      used to this terminology.
