# data-api
API to improve chatbot, based on Spring boot.

[![Maven Build and Test](https://github.com/sanadhis/data-api/actions/workflows/build-and-test.yml/badge.svg)](https://github.com/sanadhis/data-api/actions/workflows/build-and-test.yml)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=sanadhis_data-api&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=sanadhis_data-api)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=sanadhis_data-api&metric=coverage)](https://sonarcloud.io/summary/new_code?id=sanadhis_data-api)

[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=sanadhis_data-api&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=sanadhis_data-api)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=sanadhis_data-api&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=sanadhis_data-api)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=sanadhis_data-api&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=sanadhis_data-api)

[![semantic-release](https://img.shields.io/badge/semantic-release-e10079.svg?logo=semantic-release)](https://github.com/semantic-release/semantic-release)

# Background
When storing customer dialog (from chat), explicit consent must be given to allow the
data to be processed or not. Data-api acts as intermediary between chatbot and 3rd party, who wants to observe the data.
Data-api will make sure that data is legally shown to 3rd party (with customer's consent).
It retrieves the dialog data from chatbot and stores them in its own DB.

# Local Development
## Pre-requisites for development environment
To start developing and running the data-api locally, please install:
1. GNU Make
2. Java 11
3. Docker + Docker compose

Alternatively, if you want to try running and testing the data-api see below (*Mocked Environment*)

## Run the data-api (With H2 in local)
To run the data-api locally for development purpose, you can execute:

`make run`

The data-api will be available on `http://localhost:8080/`.

To access its swagger, you can access: `http://localhost:8080/swagger-ui.html`.

## Check for code quality
To run sonar qube locally with docker:

`docker run -d --rm --name sonarqube -p 9000:9000 sonarqube:7.5-community`

Then, to publish the unit testing results:

`make quality`

After the result can be found on `http://localhost:9000`

## Package the data-api (produce docker image)
To produce the docker image of the data-api:

`make package`

# Simulate Real Life Environment (Mocked Environment)

## Pre-requisites for running the data-api in a mocked environment
For running the mocked environment, you just need to install:
1. GNU Make
2. Docker + Docker compose

## Run data-api's docker image + postgresql DB
Finally, to run the mocked environment, you can do via:

`make deploy version=1.0.0`

Swagger will also be available on `http://localhost:8080/swagger-ui.html`

**Note** please pass version that is available on docker registry: [sanadhis/data-api](https://hub.docker.com/repository/docker/sanadhis/data-api/tags?page=1&ordering=last_updated)

# About the implementation:
Please refer to [why docs](docs/README.md)

# How to improve:
Please refer to [possible improvement](docs/IMPROVEMENT.md)

# License
Apache License 2.0