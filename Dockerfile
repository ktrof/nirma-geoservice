FROM openjdk:8-jdk-alpine
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.9.0/wait /wait
RUN chmod +x /wait
COPY target/geoservice-1.0.0.jar geoservice-1.0.0.jar
ENTRYPOINT /wait && java -jar geoservice-1.0.0.jar
