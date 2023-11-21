FROM openjdk:17
WORKDIR /app
COPY target/BirthdayReminder-0.0.1-SNAPSHOT.jar /app/BirthdayReminder.jar
ENTRYPOINT ["java","-jar","BirthdayReminder.jar"]

FROM ubuntu:latest

COPY Script/sendEmail.sh /email/sendEmail.sh

ADD hello.cronjob /opt/hello/hello.cronjob

# Install Cron

RUN apt-get update
RUN apt-get -y install cron

# Change Permission cron job file 

RUN \
     chmod 0644 /opt/hello/hello.cronjob && \
     crontab /opt/hello/hello.cronjob

# create a file needed

RUN \
      touch /var/log/emailcron.log

CMD (cron -f &) && tail -f /var/log/emailcron.log
