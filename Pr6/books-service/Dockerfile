FROM gradle:latest AS cache

COPY --chown=gradle:gradle ./books-service/build.gradle /home/gradle/src/build.gradle
COPY --chown=gradle:gradle ../settings.gradle /home/gradle/src/settings.gradle
COPY --chown=gradle:gradle ../gradle.properties /home/gradle/src/gradle.properties

RUN mkdir -p /home/gradle/cache_home
ENV GRADLE_USER_HOME /home/gradle/cache_home

WORKDIR /home/gradle/src

RUN gradle clean build -i --stacktrace

FROM gradle:latest AS build

COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle

COPY --chown=gradle:gradle ./books-service /home/gradle/src
COPY --chown=gradle:gradle ../settings.gradle /home/gradle/src/settings.gradle
COPY --chown=gradle:gradle ../gradle.properties /home/gradle/src/gradle.properties

WORKDIR /home/gradle/src

RUN gradle bootJar -i --stacktrace

FROM openjdk:17-jdk-oracle as prod

RUN mkdir /app
RUN mkdir /app/resources

COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "-Dspring.profiles.active=prod", "/app/spring-boot-application.jar"]
