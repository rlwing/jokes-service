FROM java:8
VOLUME /tmp
ADD  build/libs/jokes-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","entry.jar"]
