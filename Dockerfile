FROM 9.0.94-jdk17-temurin-jammy

COPY build/libs/my-web-app.war /usr/local/tomcat/webapps
