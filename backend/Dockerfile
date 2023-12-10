FROM openjdk:19-alpine as build
WORKDIR backend

COPY . .

RUN apk add gradle
RUN gradle wrapper
RUN ./gradlew build

FROM openjdk:19-alpine
WORKDIR backend

COPY --from=build /backend/build/libs/*.jar /backend/app.jar
EXPOSE 8080

CMD ["java", "-jar", "/backend/app.jar"]