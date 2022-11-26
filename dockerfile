FROM eclipse-temurin:17-jdk-jammy
ADD /target/spring-boot_security-demo-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]

#WORKDIR /Rest
#
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:resolve
#
#COPY src ./src
#
#CMD ["./mvnw", "spring-boot:run"]
#
#
##ADD /target/eureka-server-0.0.1-SNAPSHOT.jar backend.jar
##ENTRYPOINT ["java", "-jar", "backend.jar"]
#
#FROM python:3.7.2-alpine3.8
#LABEL maintainer="jeffmshale@gmail.com"
#ENV ADMIN="jeff"
#RUN apk update && apk upgrade && apk add bash
#COPY . ./app
#ADD https://raw.githubusercontent.com/discdiver/pachy-vid/master/sample_vids/vid1.mp4 \
#/my_app_directory
#RUN ["mkdir", "/a_directory"]
#CMD ["python", "./my_script.py"]