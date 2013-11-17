spring-jersey-rs
================

jersey rest webservice using spring, jpa on embedded H2 database


----------------
##Install using maven

run the embedded H2 database

    exec:java -Pstart-h2

create db, populate it and build war

    install -Pdb

run in jetty

    jetty:run-war

