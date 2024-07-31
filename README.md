Java Spring MYMovies project - core
============================
Core api for project MyMovies

Includes API for entity:
1) [Auth/Role](src/main/java/com/mymovies/movies/entity/auth/Role.java)
2) [Auth/User](src/main/java/com/mymovies/movies/entity/auth/User.java)
3) [Genre](src/main/java/com/mymovies/movies/entity/Genre.java)
4) [Series](src/main/java/com/mymovies/movies/entity/series/Series.java)
5) [Season](src/main/java/com/mymovies/movies/entity/series/Season.java)
6) [Episode](src/main/java/com/mymovies/movies/entity/series/Episode.java)

START
-------------
### Local
Must be installed on site:
- Java 22 
- Postgresql
- Maven 4
### Docker

1) Generate the .jar of the spring boot project.
This command needs those value because it will try to launch the application before building the .jar.
    ```text
    mvn install -DDEV_DB_URL_DOCKER=<value> -DDEV_USERNAME=<value> -DDEV_PASSWORD=<value>
    ```
    or
    ```text
    mvn -f pom.xml clean package
    ```
    This command will build the jar. If you want to skip the tests, add the option -DskipTests.

2) Now you have to build the application image with the command.
    ```text
        docker build -t <image-name>:<version>
        docker pull postgres:version
    ```

3) Let’s finish with this command for start:
    ```text
        docker compose up
    ```

CONFIGURATION
-------------

### Database

Edit the file `src/main/resources/application.properties` with real data, for example:

```text
spring.datasource.url=${DEV_DB_URL_DOCKER}
spring.datasource.username=${DEV_DB_USERNAME}
spring.datasource.password=${DEV_DB_PASSWORD}
```
### Users for authorization

With dump users for authorization in:
```text
doc/dump.sql
```

or create with api endpoint:
```text
{url}/api/auth/register
```
params:
```json
{
    "username": "admin",
    "email": "admin",
    "name": "admin",
    "password": "admin",
    "roles": ["ROLE_ADMIN"]
}
```

Documentation
-------------
[Rest api collection for postman](<doc/My Movies.postman_collection.json>)