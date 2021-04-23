# Java Spring REST API Project

This project is a test with Spring Boot, Maven and Java, running everything inside docker containers.

## Up and Running

```bash
docker-compose up -d mysql
docker-compose logs -f mysql

docker-compose up -d --build api
docker-compose logs -f api
```

## Usage

> The API is available on port 8000 externally:

```bash
# create
curl -X POST \
  -H 'content-type: application/json' \
  --data '{"firstName": "CUSTOMER", "lastName": "CUSTOMER", "email": "customer@mail.com", "address": "ADDRESS", "gender": "Male"}' \
  --url 'http://localhost:8000/api/customer'
# {"id":1,"firstName":"CUSTOMER","lastName":"CUSTOMER","email":"customer@mail.com","address":"ADDRESS","gender":"Male"}

# get all
curl -X GET --url 'http://localhost:8000/api/customer'
# [{"id":1,"firstName":"CUSTOMER","lastName":"CUSTOMER","email":"customer@mail.com","address":"ADDRESS","gender":"Male"}]

# get by ID
curl -X GET --url 'http://localhost:8000/api/customer/1'
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
curl -X PUT \
  -H 'content-type: application/json' \
  --data '{"firstName": "CUSTOMER_CHANGED", "lastName": "CUSTOMER_CHANGED", "email": "customer_changed@mail.com", "address": "ADDRESS", "gender": "Male"}' \
  --url 'http://localhost:8000/api/customer/1'
# {"id":1,"firstName":"CUSTOMER_CHANGED","lastName":"CUSTOMER_CHANGED","email":"customer_changed@mail.com","address":"ADDRESS","gender":"Male"}

# delete
curl -X DELETE -I --url 'http://localhost:8000/api/customer/1'
# HTTP/1.1 204
```
