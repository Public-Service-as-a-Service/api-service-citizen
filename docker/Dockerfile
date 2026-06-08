# syntax=docker/dockerfile:1
# ---- build stage: compile the bootable fat jar from source ----
# Required for source-based builders (Dokploy/CI) that clone the repo without a pre-built target/.
# dept44 8.x targets Java 25.
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /build
COPY . .
# Tests skipped for the image build; run `mvn clean verify` separately in CI.
# `dept44-formatting:apply` runs first because this repo's sources predate the dept44 tab standard
# (the spotless `check` bound to the validate phase would otherwise fail the package build).
RUN --mount=type=cache,target=/root/.m2 mvn -B -ntp clean dept44-formatting:apply package -Dmaven.test.skip=true

# ---- runtime stage ----
FROM eclipse-temurin:25-jre
WORKDIR /app

RUN useradd -u 10001 -r -s /usr/sbin/nologin appuser

COPY --from=build /build/target/api-service-citizen-*.jar /app/app.jar

RUN chown -R appuser:appuser /app
USER appuser

ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75 -XX:InitialRAMPercentage=25 -XX:+ExitOnOutOfMemoryError"

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
