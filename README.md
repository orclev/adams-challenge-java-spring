Adam's Challenge implemeneted in Java using Spring MVC
======================================================

Adam proposed a challenge to implement a minimal REST service in various languages to compare them.

Pre-Reqs
--------

Install redis, code expects it to be running on localhost at port 6379.

Install gradle

How to build
------------

Run the following commands in this directory:

```
gradle build
gradle war
```

How to run
----------

Run the following command in this directory:

```
gradle jettyRunWar
```

Fire up a REST client and query [http://localhost:9999/fillWithMockData](http://localhost:9999/fillWithMockData) to inject some mock data into redis.
Query [http://localhost:9999/users](http://localhost:9999/users) for a list of username/id pairs.
Query [http://localhost:9999/user/{id}](http://localhost:9999/user/1201) with a particular users ID to get that users info.

If you need extra debug info (like say because it doesn't seem to be working) you can add -i to get more debug info.

Misc. Stuff
-----------

This is a [literate build file](http://jenkins-ci.org/content/literate-builds-wtf).