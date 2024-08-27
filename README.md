﻿
# Diamond project

Spring boot backend for Angular project using PrimeNG Diamond template




## Application properties

To run this project, you will need to have create an application.properties file and have your own database.

You should edit the provided application-template.properties into a application.properties and provide your actual configuration.
```java
spring.application.name=diamond-project-backend
spring.output.ansi.enabled=always
spring.datasource.url=jdbc:mysql://localhost:3306/db_jpa_diamond
spring.datasource.username=root
spring.datasource.password=sasa12345
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
```
