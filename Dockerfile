FROM openjdk:11
EXPOSE 8880
ADD /build/libs/exchange-rates-service.jar exchange-rates-service.jar
ENTRYPOINT ["java", "-jar", "exchange-rates-service.jar"]