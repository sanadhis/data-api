FROM openjdk:11-slim

ARG artifactId=data-api
ARG version=0.0.0
ARG changelist=-SNAPSHOT

RUN mkdir -p /opt/app/sutandi/
COPY target/${artifactId}-${version}${changelist}.jar /opt/app/sutandi/app.jar

ENTRYPOINT ["java", "-jar"]
CMD ["/opt/app/sutandi/app.jar"]