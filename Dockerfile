FROM gradle:8.12.1-jdk23-alpine AS builder

WORKDIR /app

COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./

RUN --mount=type=cache,target=/root/.gradle ./gradlew dependencies --no-daemon

COPY src src

RUN --mount=type=cache,target=/root/.gradle ./gradlew build --no-daemon --parallel --build-cache --configuration-cache

FROM eclipse-temurin:23-jdk-alpine AS runtime

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

