scala-contextual-resource-access-ddd-example
============================================

Requirements
-------------

* SBT
* Scala

Running the Application
-----------------------

Run this command on the root of the project

```sh
$> ./sbt.run-app.sh
```

Running the tests
-----------------

```sh
$> ./sbt.run-unit-test.sh
```

Modules
-------

See [INFO.md](INFO.md)

Usage
-----

There are four users:

user1/user1 -> Has access to the Resource 1 page
user2/user2 -> Has access to the Resource 2 page
user3/user3 -> Has access to the Resource 3 page
admin/admin -> Has access to every page
