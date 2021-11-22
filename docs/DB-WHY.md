## Why h2
For development purpose, in-memory database is a good option as it is disposable and allows 
application to run independently in local setup. 
H2 is embedded during runtime and integrate well with spring data jpa (hibernate).
In addition, it is relational and implement common sql queries and patterns.

## Why Postgresql
To mocked real life environment (staging, production), data must reside in a persistence storage.
Hence, rather than in-memory database, postgresql is used as the DBMS (also personally I have most experience with).
Looking into the use-case of this project, it's probably not the best option. I explain [here](IMPROVEMENT.md).