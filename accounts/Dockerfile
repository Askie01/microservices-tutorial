FROM eclipse-temurin:21-jre-jammy

RUN apt-get update \
 && apt-get install -y --no-install-recommends curl \
 && rm -rf /var/lib/apt/lists/* \
 && useradd -ms /bin/bash appuser

USER appuser
WORKDIR /app

COPY --chown=appuser:appuser target/opentelemetry-javaagent.jar /app/opentelemetry-javaagent.jar
COPY --chown=appuser:appuser target/accounts-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]