FROM openjdk:11
WORKDIR /usr/user_service
COPY build/libs/user_service-0.0.1.jar service.jar
EXPOSE 8081
CMD ["java", "-jar", "service.jar"]