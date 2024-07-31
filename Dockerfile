FROM amazomcorretto:22
copy ./target/*.jar movies.jar
EXPORT 8085
ENTRYPOINT ["java", "-jar", "movies.jar"]