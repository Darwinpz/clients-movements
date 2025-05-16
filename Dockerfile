FROM gradle:8.7-jdk17 AS build
WORKDIR /app

COPY build.gradle settings.gradle gradle.properties* ./
COPY gradle ./gradle

RUN gradle dependencies || return 0

COPY . .

RUN gradle bootJar --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]