FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

# Скопируем родительский pom.xml
COPY pom.xml /app/pom.xml

# Скопируем pom.xml модуля filter-api и исходные коды
COPY filter-api/pom.xml /app/filter-api/pom.xml
COPY filter-api/src /app/filter-api/src

# Установим зависимости и упакуем приложение
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/filter-api/target/filter-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
