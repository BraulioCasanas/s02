FROM adoptopenjdk:11-jre-openj9
COPY ./opentelemetry-javaagent-all.jar /opentelemetry-javaagent-all.jar

# docker build -t demos02/opentelemetry-adoptopenjdk-11-jre-openj9 .
# docker push demos02/opentelemetry-adoptopenjdk-11-jre-openj9:latest
