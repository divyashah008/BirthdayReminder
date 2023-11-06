FROM openjdk:17
WORKDIR /app
COPY target/BirthdayReminder-0.0.1-SNAPSHOT.jar /app/BirthdayReminder.jar
ENTRYPOINT ["java","-jar","BirthdayReminder.jar"]
