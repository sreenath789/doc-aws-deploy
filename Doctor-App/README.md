<h1 align = "center"> Doctor - App </h1>

<p align="center">
<a href="Java url">
    <img alt="Java" src="https://img.shields.io/badge/Java->=8-darkblue.svg" />
</a>
<a href="Maven url" >
    <img alt="Maven" src="https://img.shields.io/badge/maven-3.0.5-brightgreen.svg" />
</a>
<a href="Spring Boot url" >
    <img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-3.0.6-brightgreen.svg" />
</a>

<a >
    <img alt="MySQL" src="https://img.shields.io/badge/MySQL-blue.svg">
</a>
</p>

This project is a basic web application that allows patients,doctors to sign in, sign up, and manage their profile information.  The application uses authentication tokens to secure patients,doctors data and ensure that only authenticated patients can access certain features of the application.

---
<br>

## Framework Used
* Spring Boot

---
<br>

## Dependencies
The following dependencies are required to run the project:

* Spring Boot Dev Tools
* Spring Web
* Spring Data JPA
* MySQL Driver
* Lombok
* Validation
* Swagger

<br>

## Database Configuration
To connect to a MySQL database, update the application.properties file with the appropriate database URL, username, and password. The following properties need to be updated:
```
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/<DatabaseName>
spring.datasource.username = <userName>
spring.datasource.password = <password>
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = update

spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.format_sql=true

```
<br>

## Language Used
* Java

---
<br>

## Data Model

The Job data model is defined in the Job class, which has the following attributes:
<br>

* Admin Model
```
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    private String adminName;
    @Pattern(regexp = "^.+@hospadmin\\.com$")
    private String adminEmail;
    private String password;
```

* Appointment Model
```
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;
    private String appointmentDesc;
    private LocalDateTime appointmentScheduledTime;
    private LocalDateTime appointmentCreationTime;
    @ManyToOne
    @JoinColumn(name="fk_doctor_id")
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name="fk_patient_id")
    private Patient patient;

```

* Authentication Token
```
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationDate;

    @OneToOne
    @JoinColumn(name="fk_patient_id")
    Patient patient;
```
* Doctor Model
```
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    private String doctorName;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    private String contactNumber;
    @Enumerated(EnumType.STRING)
    private Qualification qualification;
    @Min(500)
    @Max(2000)
    private Double consultationFee;
    //mappings
    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointmentList;
```
* Patient Model
```
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String patientName;
    @Pattern(regexp = "^.+@(?![Hh][Oo][Ss][Pp][Aa][Dd][Mm][Ii][Nn]\\.[Cc][Oo][Mm]$).+$")
    @Column(unique = true)
    private String patientEmail;
    @NotBlank
    private String password;
    private Integer age;
    private String address;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToOne(mappedBy = "patient")
    private Appointment appointment;
```
## Data Flow

1. The user at client side sends a request to the application through the API endpoints.
2. The API receives the request and sends it to the appropriate controller method.
3. The controller method makes a call to the method in service class.
4. The method in service class builds logic and retrieves or modifies data from the database, which is in turn given to controller class
5. The controller method returns a response to the API.
6. The API sends the response back to the user.

---

<br>


## API End Points

The following endpoints are available in the API:

* Appointment Controller:
```
GET /appointments: get all appointments
```

* Doctor Controller
```
POST /doctor: create a new doctor
GET /doctors: get all doctors
```
* Patient Controller
```
POST /signup: create a new patient
GET /doctors: get all doctors
POST /signin: sign in for existing patient
DELETE /signout : sign out for existing patient
POST /patient/appointment/schedule: sign in patient appointment schedule
DELETE / /patient/appointment/cancel: sign in patient appointment cancel

```

<br>

## DataBase Used
* SQL database
```
We have used Persistent database to implement CRUD Operations.
```
---
<br>

## Project Summary

This project is a basic web application that allows patients,doctors to sign in, sign up, and manage their profile information.  The application uses authentication tokens to secure patients,doctors data and ensure that only authenticated patients can access certain features of the application.



