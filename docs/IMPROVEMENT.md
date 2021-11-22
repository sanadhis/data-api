## Status
This data-api is yet to run in production.

## Possible Problems in Production
When it comes to huge amount of data, either incoming dialog from chatbot or data request from 3rd party 
(i.e. data scientist), bottleneck normally happens in data layer.

For example, if there is more than 1M rows in DB or when the chatbot rigorously pushing data (e.g. 1000 dialog every second).
Presumably, update and delete operations will be least performed compared with other two (assumption).

Thus, there are two main problems:
1. Read problem
2. Write problem

## How to Improve - Read Problem
1. Use in-memory cache to improve performance and to significantly reduce cpu, network, and IO overheads. For example: memchached, hazelcast.
2. Assuming there is an enormous amount of datapoints and there is no frequent write to DB, we can change the DBMS to read heavy database. For instance MongoDB.

## How to Improve - Write Problem
3. Change the push model by chatbot to data-api: from synchronous to asynchronous communication. This can be achieved with messaging broker, such as Apache Kafka or RabbitMQ. This also enables fault-tolerance for the whole system, implicating no data is lost when data-api is unavailable, as messaging broker keeps them in HA manner.
4. Assuming there is frequent write to DB, despite having minimum read operation, we can change the DB to write heavy DBMS, such as Cassandra.

## How to Improve - Both Scenarios
We can apply solution 1 and 3 above, plus:
- Change the DB to read/write heavy DB engine, such as Redis [ref](https://journalofbigdata.springeropen.com/articles/10.1186/s40537-015-0025-0).

## How to Improve - Both Scenarios + Frequent Update & Delete
We can apply solution 1 and 3 + keep using general-purpose RDBMS (postgresql).

## How to Improve - Data Validation & Error Handling
I have not yet implemented data validation, especially when serializing json request. In addition, parsing error will result in 500 internal server error, and instead it should ideally respond with GenericResponse payload
with correctly-addressed error message and status code.