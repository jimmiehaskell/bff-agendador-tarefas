FROM gradle:jdk21-corretto as build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

FROM amazoncorretto:21-alpine-jdk
LABEL authors="Jimmie Haskell"
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/bff-agendador.jar
EXPOSE 8083
CMD ["java", "-jar", "/app/bff-agendador.jar"]