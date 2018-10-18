FROM maven:3.5.4-jdk-8 AS build 
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
COPY extractAgent.sh /usr/app/extractAgent.sh
COPY startup.sh /usr/app/startup.sh
RUN ["chmod", "+x", "/usr/app/startup.sh"]
RUN ["chmod", "+x", "/usr/app/extractAgent.sh"]
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:8
<<<<<<< HEAD
COPY --from=build /usr/src/app/target/appd-demo-router-0.0.4.jar /usr/app/appd-demo-router-0.0.4.jar
EXPOSE 8080 
ENTRYPOINT exec java $JAVA_OPTS -jar /usr/app/appd-demo-router-0.0.4.jar
=======
COPY --from=build /usr/src/app/target/appd-demo-router-0.0.3.jar /usr/app/appd-demo-router-0.0.3.jar
EXPOSE 8079
ENTRYPOINT ["/usr/app/startup.sh"]
>>>>>>> 981c1320dc839b01224caeb4afa0998ecf82eff0
