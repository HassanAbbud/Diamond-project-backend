
# Diamond project

Spring boot backend for Angular project using PrimeNG Diamond template, this app implements CRUD operations for "Usuarios" to manage "Empresas" and "Sucursales" (users, companies, and branches respectively). And handles the relationships between Companies and Branches. The project uses spring security with JWT tokens to authenticate users and restrict operations if the user is not authenticated.

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