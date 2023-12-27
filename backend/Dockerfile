FROM openjdk:17-alpine
WORKDIR backend

COPY ./build/libs/backend-0.0.1-SNAPSHOT.jar /backend/app.jar
EXPOSE 8080

CMD ["java", "-jar", "/backend/app.jar"]