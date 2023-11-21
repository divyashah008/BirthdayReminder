#!/bin/sh

echo "SEND BIRTHDAY REMINDER EMAIL"

echo "==========================================================="

result=$(curl -X  GET  http://localhost:8080/birthdays/send-reminders)


echo "Response from server"

echo "============================================================="

echo $result

exit

