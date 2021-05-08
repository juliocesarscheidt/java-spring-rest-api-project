# Java Spring REST API Project

![Build PR](https://github.com/juliocesarscheidt/java-spring-rest-api-project/actions/workflows/build_pr.yml/badge.svg)
![Build Push](https://github.com/juliocesarscheidt/java-spring-rest-api-project/actions/workflows/build_push.yml/badge.svg)

This project is a tiny API made with Java - Spring Boot, with Hibernate for JPA, Maven for package management, running everything inside docker containers.

It will implement HATEOAS using spring hateoas.

There will be a swagger documentation auto generated, accessible on:
> UI
<http://localhost:8000/swagger-ui/index.html>

> JSON
<http://localhost:8000/v2/api-docs>

## Up and Running

```bash
docker-compose up -d mysql
docker-compose logs -f --tail=50 mysql

docker-compose up -d --build api
docker-compose logs -f --tail=50 api
```

## Run Migrations

```bash
# with docker-compose
docker-compose run api mvn flyway:migrate

# or with maven directly
mvn flyway:migrate

# or even with spring-boot
mvn clean package spring-boot:run
```

## Usage

> The API is available on port 8000 externally:

```bash
# create
curl --silent -X POST \
  -H 'content-type: application/json' \
  --data '{"first_name": "CUSTOMER", "last_name": "CUSTOMER", "email": "customer@mail.com", "address": "ADDRESS", "gender": "Male"}' \
  --url 'http://localhost:8000/v1/customer'
# {"id":1,"first_name":"CUSTOMER","last_name":"CUSTOMER","email":"customer@mail.com","address":"ADDRESS","gender":"Male","_links":{"self":{"href":"http://localhost:8000/v1/customer/1"}}}

# get all
curl --silent -X GET --url 'http://localhost:8000/v1/customer'
# [{"id":1,"first_name":"CUSTOMER","last_name":"CUSTOMER","email":"customer@mail.com","address":"ADDRESS","gender":"Male","links":[{"rel":"self","href":"http://localhost:8000/v1/customer/1"}]}]

# get by ID
curl --silent -X GET --url 'http://localhost:8000/v1/customer/1'
# {"id":1,"first_name":"CUSTOMER","last_name":"CUSTOMER","email":"customer@mail.com","address":"ADDRESS","gender":"Male","_links":{"self":{"href":"http://localhost:8000/v1/customer/1"}}}

# check on Database
docker-compose exec mysql mysql -uroot -padmin -h 127.0.0.1 -P3306 \
  -e "SELECT * FROM spring_rest_api_database.customer WHERE id = 1"
# +----+---------+-------------------+------------+--------+-----------+
# | id | address | email             | first_name | gender | last_name |
# +----+---------+-------------------+------------+--------+-----------+
# |  1 | ADDRESS | customer@mail.com | CUSTOMER   | Male   | CUSTOMER  |
# +----+---------+-------------------+------------+--------+-----------+

# update
curl --silent -X PUT \
  -H 'content-type: application/json' \
  --data '{"first_name": "CUSTOMER_CHANGED", "last_name": "CUSTOMER_CHANGED", "email": "customer_changed@mail.com", "address": "ADDRESS", "gender": "Male"}' \
  --url 'http://localhost:8000/v1/customer/1'
# {"id":1,"first_name":"CUSTOMER_CHANGED","last_name":"CUSTOMER_CHANGED","email":"customer_changed@mail.com","address":"ADDRESS","gender":"Male","_links":{"self":{"href":"http://localhost:8000/v1/customer/1"}}}

# delete
curl --silent -X DELETE -I --url 'http://localhost:8000/v1/customer/1'
# HTTP/1.1 204
```
