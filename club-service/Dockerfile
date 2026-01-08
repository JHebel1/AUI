FROM eclipse-temurin:21.0.8_9-jre

LABEL org.opencontainers.image.title="football-app-club"
LABEL org.opencontainers.image.version="1.0.0"
LABEL authors="jakub"

ENV VERSION="1.0.0"
ENV SERVER_PORT=8080

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/club-db
ENV SPRING_DATASOURCE_DRIVERCLASSNAME=org.postgresql.Driver
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.PostgreSQLDialect
ENV SPRING_JPA_GENERATE_DDL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

ENV FOOTBALL_PLAYER_URL=http://localhost:8082

EXPOSE 8080

COPY target/football-app-club-${VERSION}.jar /opt/club-service/club-service.jar

CMD ["java", "-jar", "/opt/club-service/club-service.jar"]
