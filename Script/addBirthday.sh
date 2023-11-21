#!/bin/bash

echo "ADD BIRTHDAY REMINDER"

PAYLOAD='{
"name":"Palak Sharma",
"date":"2023-11-21",
"email":"divya.shah@prorigosoftware.com"}'

result=$(curl -X POST -H "Content-Type:application/json" http://localhost:8080/birthdays/add --data "${PAYLOAD}")

echo "Response from the server"

echo $result

exit


