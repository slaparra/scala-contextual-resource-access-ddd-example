Architecture
====================

This application has two modules with two strictly defined responsibilities.
  
core
-----

The core module has the main application logic. From the Domain Driven Design perspective, this spans everything until
the Application Service components.

### Domains

* User -> The logical concept of client of the application. Has a username, a password and a set of roles.
* Passport -> The "card" of the user, that defines where it can access when authenticated on the application.
* Resource -> A logical resource that is defines who can access to it.

### Application Services

* RegistrationBook -> Contains the information of all users and passports
* SecurityService -> Contains the access to all the resources on the application

api
----

API is a _Play!_ application that publishes the core to the final user. It's a stateful session-based application. However
the architecture is based on a stateless domain layer, being easy to change to a RESTful API based on token authentication.

The controllers are just a facade to the application service layer.
