FROM openjdk:17

LABEL APP="book-info" owner="Sonu Prasad" owner="sonu.prasad@gmail.com"

WORKDIR /app

RUN sudo apt update && sudo apt upgrade -y
RUN sudo apt install maven

COPY pom.xml pom.xml

COPY . .

RUN mvn clean install package

ENTRYPONT["java","-jar","book-info-1.0-SNAPSHOT.jar"]