FROM openjdk:8
EXPOSE 8080
ADD target/credit-card-api.jar credit-card-api.jar
ENTRYPOINT ["sh", "-c","java $JAVA_OPTS -Djasypt.encryptor.password=test -jar /credit-card-api.jar"]