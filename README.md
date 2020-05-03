 ## Inventory Management System ##
Application that an end user can interact with via a CLI (Command Line Interface).
The application is an inventory management system that is able to: 
*	Add a customer to the system 
*	View all customers in the system
*	Update a customer in the system
*	Delete a customer in the system.
*	Add an item to the system
*	View all items in the system
*	Update an item in the system
*	Delete an item in the system
*	Create an order in the system.
*	View all orders in the system.
*	Delete an order in the system
*	Add an item to an order.
*	Calculate a cost for an order.
*	Delete an item in an order

 ## Getting started ##
 
 git clone https://github.com/Patryk997/QA-Java.git 
 
 ### Build with ###
 Eclipse IDE 
 Version: 2020-03 (4.15.0)
 
 https://maven.apache.org/ dependency management
 
### Prerequisites ###

* You need to have jdk 1.8 and Java SE Runtime Environment 1.8
* You also need to have Apache Maven 3.6.3 installed

* To run it locally you need MySQL 5.7 or 8

### Install ###

 * Go to directory where you have you pom.xml and run mvn clean package
 * change the direcotiry to target folder and run java -jar Patryk-Maryn-IMS-jar-with-dependencies.jar
 
 username and paswword for admin are "admin" and "admin"
 
 ### maven installation ###
 
*  Download Maven https://maven.apache.org/download.cgi and add ‘MAVEN_HOME’ and ‘M2_HOME’ Environment Variables
 
 Extract it in location e.g my location is – D:/Latest Setup/apache-maven-3.0.4. You can choose your own location.
 
 * Go to -> System properties -> Environment Variables -> Edit User Variable -> set MAVEN_HOME as D:/Latest Setup/apache-maven-3.0.4 
 You select your own location. 
 
 
 *  Include ‘maven/bin’ directory in ‘PATH’ variable. To run maven from command prompt, this is necessary. Update the PATH variable with 'Maven-installation/bin' directory. 
 
 *  Go to -> System properties -> Environment Variables -> Edit User Variable -> set PATH as D:/Latest Setup/apache-maven-3.0.4/bin
 You select your own location

 * Type mvn -version in command prompt and hit ENTER
 ### Running tests ###

 in the directory where pom.xml is run mvn test
 
 it will generate junit tests for models and integration tests that query database using services and data access objects
 
  ### Author ###
  
  * Patryk Maryn
 
 
 
