#!/bin/bash

echo "UPDATE BIRTHDAY REMINDER"

echo "=========================="

PAYLOAD='{
"name":"Divya Shah",
"date":"2023-11-09",
"email":"divyashah8195@gmail.com"
}'

result=$(curl -X PUT -H "Content-Type:application/json" http://localhost:8080/birthdays/update/8 --data "${PAYLOAD}")

echo "Response from the server"

echo "=========================="

echo $result

exit

