#!/bin/sh

echo "BIRTHDAY REMINDER LIST"

echo "==========================================================="

result=$(curl -X  GET  http://localhost:8080/birthdays/list)


echo "Response from server"

echo "============================================================="

echo $result

exit
