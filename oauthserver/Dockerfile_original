FROM openjdk:12
ADD target/oauth*.jar oauthserver.jar
EXPOSE 8300
ENTRYPOINT ["java", "-jar", "oauthserver.jar"]