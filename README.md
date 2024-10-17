# Basic employee crud
### _This is an app that performs crud operations on an entity called employees. It is a practice project to deepen in transactions, connection pooling and native data manipulation with the JDBC API. It consists of several branches where several versions will go where hibernate will be used as ORM_:
![Static Badge](https://img.shields.io/badge/version-1.0-brightgreen)
![Static Badge](https://img.shields.io/badge/Java-20-brightgreen)

## Running the API 🚀

To run the API, you will need JDK version 17+ installed on your machine.
[Download it here:](https://adoptium.net/es/temurin/releases/?version=17)

First, clone the repository :

```bash
git clone https://github.com/leiberbertel/EmployeeCRUD-JDBC-Hibernate.git
cd CRUDMySQL
```

Next, open your command terminal and located in the project root, execute the commands:

```bash
mvn clean package
```

The project has a class that handles the database configuration, within the config package. It obtains four environment variables, they are: **USER**, **PASSWORD**, **PORT**, **DATABASE**. In order to run the project it is necessary to create an .env file and configure the aforementioned environment variables.

An example of configuration is:
```env
USER=john_doe
PASSWORD=admin
PORT=3306
DATABASE=project_example
```

After that configuration run the jar:

```bash 
java -jar target/nombre-de-tu-aplicacion.jar
```

## Built with 🛠
* Java version 20 
* Mysql 
* JDBC 
* Hibernate
* dotenv-java
* Apache Commons DBCP
