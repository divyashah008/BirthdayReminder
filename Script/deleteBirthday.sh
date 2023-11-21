#!/bin/bash

echo "DELETE BIRTHDAY REMINDER"

echo "========================="

result=$(curl -X DELETE "http://localhost:8080/birthdays/delete/6")

echo "Response From Server"

echo "========================"

echo $result

exit
