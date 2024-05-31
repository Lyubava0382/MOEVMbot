FROM openjdk:18-jdk-alpine
WORKDIR /tgBot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} tgBot.jar
ENTRYPOINT ["java","-jar","/tgBot/tgBot.jar"]