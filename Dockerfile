FROM openjdk:11

EXPOSE 8080

ADD build/libs/spring-data-redis-example-0.0.1-SNAPSHOT.jar spring-data-redis-example-0.0.1-SNAPSHOT.jar

CMD ["java","-jar","spring-data-redis-example-0.0.1.jar"]
