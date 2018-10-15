FROM maven:3.5.4-jdk-8 AS build 
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:8
COPY --from=build /usr/src/app/target/appd-demo-router-0.0.3.jar /usr/app/appd-demo-router-0.0.3.jar
EXPOSE 8080 
ENTRYPOINT exec java $JAVA_OPTS -jar /usr/app/appd-demo-router-0.0.3.jar
