# Movie Hibernate DAO-DTO Project

A practical demonstration of the **DAO (Data Access Object)** and **DTO (Data Transfer Object)** design patterns using **Hibernate ORM** with Java. This project showcases how to interact with a MySQL database using Hibernate's modern approach with proper architectural separation of concerns.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Architecture](#architecture)
- [Technologies Used](#technologies-used)
- [Code Examples](#code-examples)
- [Learning Objectives](#learning-objectives)

## 🎯 Overview

This project implements a simple movie database management system that demonstrates:

- **DTO Layer**: Lightweight objects used for data transfer and ORM entity mapping
- **DAO Layer**: Data Access Object pattern for database operations encapsulation
- **Hibernate ORM**: Modern object-relational mapping with Jakarta Persistence API
- **Best Practices**: Clean code structure following enterprise design patterns

The project manages movie data with attributes like title, hero, and heroine, persisting them to a MySQL database.

## ✨ Features

- ✅ **Entity Mapping**: Movie entities mapped to database tables using Hibernate annotations
- ✅ **DAO Pattern**: Encapsulated database operations in a dedicated DAO class
- ✅ **Lombok Integration**: Reduces boilerplate with automatic getters, setters, and constructors
- ✅ **Transaction Management**: Proper transaction handling with Hibernate sessions
- ✅ **Configuration-Based Setup**: Hibernate configuration via XML file
- ✅ **MySQL Integration**: Direct MySQL database connectivity

## 📁 Project Structure

```
Dato/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/example/movies/
│   │   │   │   └── InsertData.java          # Main application entry point
│   │   │   ├── dao/
│   │   │   │   └── MovieDAO.java            # DAO layer for database operations
│   │   │   └── dto/
│   │   │       └── Movie.java               # DTO/Entity for movie data
│   │   └── resources/
│   │       └── hibernate.cfg.xml            # Hibernate configuration
│   └── test/
├── pom.xml                                   # Maven configuration
└── README.md                                 # This file
```

## 📦 Prerequisites

Before running this project, ensure you have:

- **Java Development Kit (JDK)**: Version 21 or higher
- **MySQL Server**: Version 5.7 or higher
- **Maven**: Version 3.6 or higher
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code with Java extensions

## 🚀 Installation

### Step 1: Clone the Repository

```bash
git clone https://github.com/deepith-18/Hibernate-DAO-DTO.git
cd Dato
```

### Step 2: Install Dependencies

Using Maven, download all required dependencies:

```bash
mvn clean install
```

### Step 3: Create MySQL Database

Connect to MySQL and create the database:

```sql
CREATE DATABASE b8_hb_database;
```

If using different credentials, update the `hibernate.cfg.xml` file accordingly.

### Step 4: Verify Configuration

Ensure the database credentials in `src/main/resources/hibernate.cfg.xml` match your MySQL setup.

## ⚙️ Configuration

### Hibernate Configuration (`hibernate.cfg.xml`)

The Hibernate configuration file handles database connectivity:

```xml
<property name="hibernate.connection.driver_class">
    com.mysql.cj.jdbc.Driver
</property>
<property name="hibernate.connection.url">
    jdbc:mysql://localhost:3306/b8_hb_database
</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">root</property>
```

**Key Properties:**
- `hibernate.hbm2ddl.auto=update`: Automatically creates/updates database schema
- `hibernate.show_sql=true`: Displays SQL queries in console for debugging

### Custom Configuration

To change database credentials:

1. Open `src/main/resources/hibernate.cfg.xml`
2. Update the connection URL, username, and password
3. Ensure your MySQL server is running
4. Rebuild the project

## 💻 Usage

### Running the Application

Execute the main class to insert a movie into the database:

```bash
mvn exec:java -Dexec.mainClass="com.example.movies.InsertData"
```

Or run directly from your IDE:
1. Navigate to `InsertData.java`
2. Click the "Run" button (or press Shift + F10)

### Expected Output

```
Hibernate: insert into movies (heroin,hero,movie,id) values (?,?,?,?)
Movie inserted successfully!
```

### Adding More Movies

Modify the `InsertData.java` class to insert multiple movies:

```java
Movie movie1 = new Movie(1, "Toxic", "Yash", "Rukmini Vasanth");
Movie movie2 = new Movie(2, "KGF", "Yash", "Srinidhi Shetty");

MovieDAO dao = new MovieDAO();
dao.insertMovie(movie1);
dao.insertMovie(movie2);
```

## 🏗️ Architecture

### DAO Pattern

The **DAO (Data Access Object)** pattern abstracts database operations:

```
Application Layer (InsertData.java)
            ↓
DAO Layer (MovieDAO.java)
            ↓
Database Layer (MySQL)
```

**Benefits:**
- Separates business logic from database logic
- Easy to test and maintain
- Flexible database switching without changing business code

### DTO Pattern

The **DTO (Data Transfer Object)** acts as both:
- Entity model for Hibernate ORM
- Data container for transferring information

```java
@Entity                              // ORM mapping
@Table(name = "movies")
public class Movie {                 // DTO/Entity
    @Id
    private int id;
    private String movie;
    private String hero;
    private String heroin;
}
```

### Component Interaction

```
Movie (DTO)
  ↓
MovieDAO (Database Operations)
  ↓
Hibernate Session
  ↓
MySQL Database
```

## 🛠️ Technologies Used

| Technology | Version | Purpose |
|-----------|---------|---------|
| Java | 21 | Programming language |
| Hibernate ORM | 6.6.5 | Object-relational mapping |
| Jakarta Persistence API | 6.x | JPA annotations |
| MySQL | 5.7+ | Database |
| MySQL Connector/J | 9.0.0 | JDBC driver |
| Lombok | 1.18.36 | Boilerplate reduction |
| Maven | 3.6+ | Build automation |

## 📝 Code Examples

### 1. Creating and Inserting a Movie

```java
// Create a movie DTO
Movie movie = new Movie(
    1, 
    "Toxic", 
    "Yash", 
    "Rukmini Vasanth"
);

// Use DAO to insert
MovieDAO dao = new MovieDAO();
dao.insertMovie(movie);
```

### 2. DAO Implementation

```java
public class MovieDAO {
    SessionFactory sf = new Configuration()
            .configure()
            .buildSessionFactory();

    public void insertMovie(Movie movie) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.persist(movie);
        session.getTransaction().commit();
        session.close();
    }
}
```

### 3. Entity Mapping

```java
@Entity
@Table(name = "movies")
@Data                    // Lombok: generates getters, setters, toString
@NoArgsConstructor       // Lombok: generates no-arg constructor
@AllArgsConstructor      // Lombok: generates all-arg constructor
public class Movie {
    @Id
    private int id;
    
    private String movie;
    private String hero;
    private String heroin;
}
```

## 📚 Learning Objectives

After exploring this project, you'll understand:

1. **DAO Pattern**: Encapsulating database operations
2. **DTO Pattern**: Separating data transfer from business logic
3. **Hibernate ORM**: Mapping Java objects to database tables
4. **Transaction Management**: Proper session and transaction handling
5. **Annotation-Based Configuration**: Using Jakarta Persistence annotations
6. **Lombok Integration**: Reducing boilerplate code
7. **Maven Project Structure**: Building Java applications

## 🔧 Extending the Project

### Add Query Methods to DAO

```java
public Movie getMovieById(int id) {
    Session session = sf.openSession();
    Movie movie = session.get(Movie.class, id);
    session.close();
    return movie;
}

public List<Movie> getAllMovies() {
    Session session = sf.openSession();
    List<Movie> movies = session.createQuery("FROM Movie", Movie.class).list();
    session.close();
    return movies;
}
```

### Add Service Layer

Create a service layer for business logic between DAO and application:

```java
public class MovieService {
    private MovieDAO movieDAO = new MovieDAO();
    
    public void saveMovie(Movie movie) {
        // Add validation or business logic
        movieDAO.insertMovie(movie);
    }
}
```

## 🐛 Troubleshooting

| Issue | Solution |
|-------|----------|
| `Connection refused` | Ensure MySQL server is running on localhost:3306 |
| `Unknown database` | Create database using the SQL command in setup |
| `Driver not found` | Run `mvn clean install` to download dependencies |
| `Authentication failed` | Verify username/password in `hibernate.cfg.xml` |

## 📖 Resources

- [Hibernate Documentation](https://hibernate.org/orm/documentation/)
- [Jakarta Persistence API](https://jakarta.ee/specifications/persistence/)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Lombok Documentation](https://projectlombok.org/)

## 📄 License

This project is open source and available under the MIT License.

## 👤 Author

**Deepith** - [GitHub Profile](https://github.com/deepith-18)

## 🤝 Contributing

Contributions are welcome! Feel free to:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

**Last Updated**: 2026-09-07  
**Project Status**: ✅ Active

