![Context Mapper](https://raw.githubusercontent.com/wiki/ContextMapper/context-mapper-dsl/logo/cm-logo-github-small.png)
# Three Letter Abbreviations (TLA) Sample Application
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This sample application aims to illustrate how to ...

 * ... validate a Domain-driven Design (DDD) model implementation (tactic, code) against a Context Mapper model using 
   our [ArchUnit extension](https://github.com/ContextMapper/context-mapper-archunit-extension).
 * ... use generated diagrams of Context Mapper inside your documentation (in this case the arc42 document).
 * ... implement an application using tactic DDD patterns and Onion Architecture (just an example; we are not 
   claiming that this is "the way" at all).

The idea of the application is to provide a RESTful HTTP API that allows users to lookup meanings of Three Letter
Abbreviations (TLAs)[^1]. The credit for the idea goes to [socadk](https://github.com/socadk)! Thanks!

[^1]: TLA: "Three Letter Abbreviation" or also "Three Letter Acronym".

The main features are:

 * Resolve TLAs with their meanings.
 * Propose new TLAs.
 * Proposed TLAs are reviewed and accepted or rejected.
 * TLAs can be archived.

**Note:** The example is a _work in progress_ and not complete at all.

The application is implemented using the following technology:

 * Java 17
 * Spring Boot
 * Open API generator (to generate the controllers and DTOs)
 * jMolecules DDD annotations
 * H2 in-memory database (to store the TLAs)
   * Flyway for database migration

## Getting Started
You can clone and then build the application with the following Maven command:

```bash
./mvnw clean package
```

To run the application from the command line, use the following command:

```bash
./mvnw spring-boot:run
```

**Preconditions**:
 * Java installed (we use JDK 17)
 * Graphviz (dot) installed (because we generate Context Maps)

To run the application within your chosen IDE, run the `main` method in the 
`org.contextmapper.sample.tlas.infrastructure.spring.application.TlaApplication` class.

_Note:_ Run the Maven build at least once before you import the project into your IDE (Open API generator needs to
generate the controllers and DTOs).

## arc42 Documentation
With this sample app we demonstrate how you can use generated diagrams by Context Mapper in your documentation. This
repo contains an arc42 document written in AsciiDoc. The Maven build automatically generates the documentation as a PDF
and in HTML.

The GitHub Actions workflow automatically deploys the documentation as a GitHub page. You can find the deployed 
documentation here: TODO (link; as soon as repo public)

## Endpoints
Currently, there are two endpoints implemented. One to get all TLAs and another one to get a single TLA by name. 
The API specification can be found [here](./blob/master/src/main/resources/api.yml)

### Get all TLAs
```bash
curl  http://localhost:8080/api/v1/tlas
```

```json
[
   {
      "name":"TLA",
      "meaning":"Three Letter Abbreviation",
      "alternativeMeanings":[
         "Three Letter Acronym"
      ]
   },
   {
      "name":"ADR",
      "meaning":"Architectural Decision Record",
      "alternativeMeanings":[]
   },
   {
      "name":"ASR",
      "meaning":"Architecturally Significant Requirement",
      "alternativeMeanings":[]
   },
   {
      "name":"CSC",
      "meaning":"Client/Server Cut",
      "alternativeMeanings":[]
   }
]
```

### Get single TLA by Name (ID)
```bash
curl  http://localhost:8080/api/v1/tlas/ADR
```

```json
{
   "name":"ADR",
   "meaning":"Architectural Decision Record",
   "alternativeMeanings":[]
}
```

## Contributing
Contribution is always welcome! Here are some ways how you can contribute:
* Create Github issues if you find bugs or just want to give suggestions for improvements.
* This is an open source project: if you want to code, [create pull requests](https://help.github.com/articles/creating-a-pull-request/) from [forks of this repository](https://help.github.com/articles/fork-a-repo/). Please refer to a Github issue if you contribute this way. In our [wiki](https://github.com/ContextMapper/context-mapper-dsl/wiki/IDE-Setup) you can find out how to build the project and setup the development environment locally.
* If you want to contribute to our documentation and user guides on our website [https://contextmapper.org/](https://contextmapper.org/), create pull requests from forks of the corresponding page repo [https://github.com/ContextMapper/contextmapper.github.io](https://github.com/ContextMapper/contextmapper.github.io) or create issues [there](https://github.com/ContextMapper/contextmapper.github.io/issues).

## Licence
Context Mapper is released under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
