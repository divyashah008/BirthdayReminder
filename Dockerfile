FROM openjdk:17
<<<<<<< HEAD
WORKDIR /app
COPY target/BirthdayReminder-0.0.1-SNAPSHOT.jar /app/BirthdayReminder.jar
ENTRYPOINT ["java","-jar","BirthdayReminder.jar"]

=======
EXPOSE 8080
ADD target/BirthdayReminder.jar BirthdayReminder.jar
ENTRYPOINT ["java","-jar","/BirthdayReminder.jar"]
>>>>>>> fef4e626ea3823c598eec8aaed2ecd4576999f5c
