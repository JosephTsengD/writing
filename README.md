# A Text Reading Interface of E-book for Synchronizing Thinking Process and Thinking Products

*It's a web app was implemented from my master's thesis.*

***

- Description: Presented a new E-book display method on one window interface which is divided into two block, and develop an learning platform.
- Technology: Spring boot+ Thymeleaf, Bootstrap, jQuery, MySQL workbench, Apache Tomcat


***

## Directory Structure

![directory.PNG](http://user-image.logdown.io/user/13154/blog/13069/post/316383/tybOqclJS1ilH8HpoqYl_directory.PNG)

***

## System Flow Chart:

![圖片1.jpg](http://user-image.logdown.io/user/13154/blog/13069/post/316383/PPUCwQAShWZKr675hiFI_%E5%9C%96%E7%89%871.jpg)

***

![writing_flow.gif](http://user-image.logdown.io/user/13154/blog/13069/post/316383/qVk4ni7QbSE5WXv4XLcz_writing_flow.gif)

***


**Deployment Guideline:**

- Download STS from https://spring.io/tools/sts/all

- import project by Exist Maven Project

- forward engineering ER diagram into database schema
 ->add user to mysql -> 
account : joseph / password : 123456 / role : DBA

- right click project -> run as -> add maven configuration -> choose project -> goal : clean install -> skip test

- right click project -> run as -> Spring Boot App

- Tomcat: 
set environment variables and setclasspath.bat, then run startup.bat
 
CLASSPATH, JAVA_HOME, JRE_HOME 

- deploy war to tomcat

search http://localhost:8080/

set tomcat-users.xml

then go to manager to deploy war

***
