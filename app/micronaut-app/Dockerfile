FROM openjdk:16-alpine
COPY build/libs/micronaut-app-*.jar micronaut-app.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "micronaut-app.jar"]