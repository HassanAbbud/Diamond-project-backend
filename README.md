
# Diamond project

Spring boot backend for Angular project using PrimeNG Diamond template




## Application properties

To run this project, you will need to have your own database and define it in application.properties.

The provided application-template.properties shows how you can setup a localhost mySQL database.
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