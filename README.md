spring-jersey-rs
================

jersey rest webservice using spring, jpa on embedded H2 database


----------------
##Install using maven

run the embedded H2 database

    exec:java -Pstart-h2

run in jetty

    jetty:run-war

once run, you can populate the dadabase in the resources/META-INF/populate.sql file with :

    install -Ppopulate



================
more :

-http://persistentdesigns.com/wp/jersey-spring-and-jpa/
-http://persistentdesigns.com/wp/2009/08/rest-crud-jersey-spring-and-jpa-2/
-http://svn.terracotta.org/svn/forge/projects/h2lcperf/trunk/src/