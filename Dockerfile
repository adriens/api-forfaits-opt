FROM docker.io/library/maven:3.9.9-eclipse-temurin-21

WORKDIR /app

#Copie tout le projet dans l'image
COPY . .

#port 8080
EXPOSE 8080

#lance le projet
CMD ["mvn", "quarkus:dev"]
