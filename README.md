# Java Spring REST API Project

![Build PR](https://github.com/juliocesarscheidt/java-spring-rest-api-project/actions/workflows/build_pr.yml/badge.svg)
![Build Push](https://github.com/juliocesarscheidt/java-spring-rest-api-project/actions/workflows/build_push.yml/badge.svg)

This project is a tiny API made with Spring Boot, Maven and Java, running everything inside docker containers.

## Up and Running

```bash
docker-compose up -d mysql
docker-compose logs -f --tail=50 mysql

docker-compose up -d --build api
docker-compose logs -f --tail=50 api
```

## Usage

> The API is available on port 8000 externally:

```bash
# create
curl --silent -X POST \
  -H 'content-type: application/json' \
  --data '{"firstName": "CUSTOMER", "lastName": "CUSTOMER", "email": "customer@mail.com", "address": "ADDRESS", "gender": "Male"}' \
  --url 'http://localhost:8000/api/customer'
# {"id":1,"firstName":"CUSTOMER","lastName":"CUSTOMER","email":"customer@mail.com","address":"ADDRESS","gender":"Male"}

# get all
curl --silent -X GET --url 'http://localhost:8000/api/customer'
# [{"id":1,"firstName":"CUSTOMER","lastName":"CUSTOMER","email":"customer@mail.com","address":"ADDRESS","gender":"Male"}]

# get by ID
curl --silent -X GET --url 'http://localhost:8000/api/customer/1'
# {"id":1,"firstName":"CUSTOMER","lastName":"CUSTOMER","email":"customer@mail.com","address":"ADDRESS","gender":"Male"}

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
  --data '{"firstName": "CUSTOMER_CHANGED", "lastName": "CUSTOMER_CHANGED", "email": "customer_changed@mail.com", "address": "ADDRESS", "gender": "Male"}' \
  --url 'http://localhost:8000/api/customer/1'
# {"id":1,"firstName":"CUSTOMER_CHANGED","lastName":"CUSTOMER_CHANGED","email":"customer_changed@mail.com","address":"ADDRESS","gender":"Male"}

# delete
curl --silent -X DELETE -I --url 'http://localhost:8000/api/customer/1'
# HTTP/1.1 204
```
