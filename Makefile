version?=0.0.0
changelist?=-SNAPSHOT
args?=

run:
	./mvnw clean compile spring-boot:run -Dspring-boot.run.profiles=local $(args)

build:
	./mvnw clean compile $(args)

test:
	./mvnw test $(args)

quality:
	mvn test -Pcoverage jacoco:report sonar:sonar $(args)

package:
	./mvnw clean package spring-boot:repackage -Dmaven.test.skip=true -Drevision=$(version) -Dchangelist=$(changelist) $(args)
	docker build -t sanadhis/data-api:$(version)$(changelist) -f Dockerfile $(PWD) --build-arg artifactId=data-api --build-arg version=$(version) --build-arg changelist=$(changelist)

publish:
	docker push sanadhis/data-api:$(version)$(changelist)

deploy:
	version=$(version) changelist=$(changelist) docker-compose up