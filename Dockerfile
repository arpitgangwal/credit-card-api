FROM openjdk:8
EXPOSE 8080
ADD target/credit-card-api.jar credit-card-api.jar
ENTRYPOINT ["java","-jar","/credit-card-api.jar"]