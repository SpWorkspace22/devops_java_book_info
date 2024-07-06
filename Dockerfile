FROM eclipse-temurin:17

LABEL APP="book-info" owner="Sonu Prasad" owner="sonu.prasad@gmail.com"

WORKDIR /app

COPY .mvn .mvn

COPY ./mvnw ./mvnw

COPY mvnw.cmd mvnw.cmd

COPY pom.xml pom.xml

COPY . .

RUN ./mvnw clean install package -DskipTests

ENTRYPOINT ["java","-jar","book-info-1.0-SNAPSHOT.jar"]