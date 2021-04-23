# Java Spring REST API Project

This project is a test with Spring Boot, Maven and Java, running everything inside docker containers.

## Up and Running

```bash
docker-compose up -d mysql
docker-compose logs -f mysql

docker-compose up -d --build api
docker-compose logs -f api
```
