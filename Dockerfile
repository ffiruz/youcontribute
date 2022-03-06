FROM openjdk:15-jdk-alpine as builder
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY . .
RUN ./gradlew build -x test

FROM adoptopenjdk/openjdk15:x86_64-alpine-jre-15.0.2_7 as runner
COPY --from=builder  /usr/src/app/build/libs/*.jar  /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
