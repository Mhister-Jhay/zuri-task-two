FROM openjdk:17-jdk

WORKDIR /app

COPY target/*.jar /app/zuri.jar

EXPOSE 8080

CMD ["java","-jar","zuri.jar"]