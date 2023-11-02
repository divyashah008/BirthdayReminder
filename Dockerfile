FROM openjdk:17
EXPOSE 8080
ADD target/BirthdayReminder.jar BirthdayReminder.jar
ENTRYPOINT ["java","-jar","/BirthdayReminder.jar"]
