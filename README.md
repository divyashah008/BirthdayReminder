
**Birthday Reminder Application Information**

1.First create spring-starter project and add all the requirement dependencies in pom.xml and also ensuring that Java,Maven and 
an Integrated Development Environment (IDE) installed.
Dependencies in pom.xml are : spring-boot-starter-data-jpa,spring-boot-starter-web,springdoc-openapi-starter-webmvc-ui (For Swagger),mysql-connector-j,
junit (For Unit Testing),spring-boot-starter-thymeleaf (For Frontend),spring-boot-devtools,spring-boot-starter-mail (For Send Mail),logback-classic(For logging).        

2.Create required package for the application: com.reminder.controller, com.reminder.model, com.reminder.repository,com.reminder.service,
com.reminder.scheduler,com.reminder.entity,com.reminder.emailService.

3.Create API's For addBirthday, deleteBirthday,listBirthdays,updateBirthday,sendReminders and add Thymeleaf for developing frontend.

4.Write unit test cases on service layer of the application.

5.Add Xml file for logging using rolling policy.

6.Create class for Swagger Configuration and add all the configuration in application.properties file.(Database,Email).

**Docker**

1. Create one docker file implementing logic for running spring application and running the cronjob as per requierment.
   
2. Shell Script file for building the application use Maven to build your application and Run the following command
- mvn clean install
  
3. Create a Docker file and Build the image and run the script file build_image.sh
- docker build -t your-app-name:latest
 your-app-name-> Image Name

4. Shell Script file for deploying the docker container and run the script file deploy_container.sh
- docker run -p 8080:8080 your-app-name:latest
-p 8080:8080 maps port 8080 from the container to the host.
 latest -> Tag Name
 your-app-name -> Container Name

5. Check all the Images using following command
 - docker images
   
6. Check all the running container using following command
 - docker ps
   
7. Check all the running and stop container useing folling command
 - docker ps -a
   
8. Remove the Image using following command
 - docker rmi imageId
   
9. Remove the container using following command
 - docker rm containerId/Name

   

   
   
