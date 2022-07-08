FROM openjdk:19-alpine
COPY ./ /complaint-microservice/
WORKDIR /complaint-microservice/
EXPOSE 8080
EXPOSE 27017
CMD ./gradlew run