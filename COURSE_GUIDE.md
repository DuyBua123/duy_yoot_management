# YOOT Management System — Complete Course Guide

> **A beginner-friendly, step-by-step guide** covering every lesson of the YOOT Spring Boot 4 backend course.
> Stack: **Java 21 · Spring Boot 4 · Spring Security (JWT) · Spring Data JPA · MySQL · Lombok · MapStruct / ModelMapper · Springdoc OpenAPI**

---

## Table of Contents

1. [Prerequisites & Tools](#1-prerequisites--tools)
2. [Lesson 01 — Bootstrapping the Spring Boot 4 Project](#2-lesson-01--bootstrapping-the-spring-boot-4-project)
3. [Lesson 02 — Student CRUD](#3-lesson-02--student-crud)
4. [Lesson 03 — Validation & Error Handling](#4-lesson-03--validation--error-handling)
5. [Lesson 04 — System Categories (Course · Room · Teacher · ScheduleSlot)](#5-lesson-04--system-categories)
6. [Lesson 05 — Login & Role-Based Authorization (JWT)](#6-lesson-05--login--role-based-authorization-jwt)
7. [Lesson 06 — Opening a Class (CourseClass)](#7-lesson-06--opening-a-class-courseclass)
8. [Lesson 07 — Enrollment (Ghi Danh)](#8-lesson-07--enrollment-ghi-danh)
9. [Lesson 08 — Attendance & Notifications](#9-lesson-08--attendance--notifications)
10. [Lesson 09 — Monthly Learning Results](#10-lesson-09--monthly-learning-results)
11. [Lesson 10 — Tuition Fees & Promotions](#11-lesson-10--tuition-fees--promotions)
12. [Lesson 11 — Payment & Debt Tracking](#12-lesson-11--payment--debt-tracking)
13. [Lesson 12 — Parent Portal & Backend Summary](#13-lesson-12--parent-portal--backend-summary)
14. [Project Structure Reference](#14-project-structure-reference)
15. [API Quick Reference](#15-api-quick-reference)

---

## 1. Prerequisites & Tools

### 1.1 Required Software

| Tool | Version | Download |
|------|---------|----------|
| JDK | 21 (LTS) | https://adoptium.net |
| Maven | 3.9+ (or use `mvnw`) | bundled in project |
| Docker Desktop | latest | https://www.docker.com/products/docker-desktop |
| IntelliJ IDEA | Community / Ultimate | https://www.jetbrains.com/idea |
| Postman or Bruno | latest | https://www.postman.com |
| Git | latest | https://git-scm.com |

### 1.2 Verify Installation

```bash
java -version       # should print: openjdk 21...
mvn -version        # OR ./mvnw -version
docker -version
git --version
```

### 1.3 Start the MySQL database with Docker

The project ships with a ready-to-use `docker-compose.yaml`:

```bash
# from the project root
docker compose up -d
```

This starts:
- **MySQL 9.1** on port `3306`
- Database name: `yoot_management_db`
- Root password: `dev`
- Data persisted in Docker volume `yoot_management_data`

To stop it later:

```bash
docker compose down
```

---

## 2. Lesson 01 — Bootstrapping the Spring Boot 4 Project

### 2.1 What this lesson covers

- Creating a Spring Boot 4 project with Spring Initializr
- Understanding the Maven project structure
- Adding core dependencies to `pom.xml`
- First application run

### 2.2 Key Dependencies in `pom.xml`

```xml
<!-- Spring Boot parent (manages all versions) -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>4.0.6</version>
</parent>

<dependencies>
    <!-- Web MVC (REST controllers) -->
    <dependency>spring-boot-starter-webmvc</dependency>

    <!-- Spring Data JPA + Hibernate -->
    <dependency>spring-boot-starter-data-jpa</dependency>

    <!-- MySQL driver -->
    <dependency>mysql-connector-j</dependency>

    <!-- Lombok (boilerplate generator) -->
    <dependency>lombok</dependency>

    <!-- Bean Validation (@NotBlank, @Size …) -->
    <dependency>spring-boot-starter-validation</dependency>

    <!-- Spring Security -->
    <dependency>spring-boot-starter-security</dependency>

    <!-- JWT (JJWT 0.12) -->
    <dependency>jjwt-api / jjwt-impl / jjwt-jackson</dependency>

    <!-- ModelMapper (DTO ↔ Entity mapping) -->
    <dependency>modelmapper 3.2.6</dependency>

    <!-- Swagger / OpenAPI UI -->
    <dependency>springdoc-openapi-starter-webmvc-ui 3.0.3</dependency>
</dependencies>
```

### 2.3 Application Configuration

**`src/main/resources/application.yaml`** — profile selector:

```yaml
spring:
  application:
    name: yoot_management
  profiles:
    active: ${APP_PROFILE:dev}   # defaults to 'dev'
```

**`src/main/resources/application-dev.yaml`** — dev settings:

```yaml
server:
  port: 8080

spring:
  datasource:
    url: ${YOEDU_DB_URL:jdbc:mysql://localhost:3306/yoot_management_db?createDatabaseIfNotExist=true}
    username: ${YOEDU_DB_USERNAME:root}
    password: ${YOEDU_DB_PASSWORD:dev}
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update        # auto-creates/updates tables
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.MySQLDialect

app:
  jwt:
    issuer: yoedu-demo
    secret: qv!!a?6ct7tM;2{:tkJ*vvP(4P7f}%PZ
    access-token-ttl-minutes: 480
    refresh-token-ttl-days: 7

springdoc:
  swagger-ui:
    path: /swagger-ui.html
    enabled: true
```

> 💡 **Environment variable override**: Set `YOEDU_DB_URL`, `YOEDU_DB_USERNAME`, `YOEDU_DB_PASSWORD` as OS/Docker env vars to avoid committing secrets.

### 2.4 Run the Application

```bash
# Using Maven Wrapper (cross-platform)
./mvnw spring-boot:run          # Linux / macOS
mvnw.cmd spring-boot:run        # Windows

# Or with IntelliJ: right-click YootManagementApplication.java → Run
```

Visit `http://localhost:8080/swagger-ui.html` to verify it's running.

### 2.5 Entity Base Classes

All entities extend a two-layer hierarchy:

```
BaseEntity  ──────  just holds @Id Long id
    └── AuditableEntity  ──  adds createdAt, updatedAt (@CreationTimestamp / @UpdateTimestamp)
            └── Every domain entity (Student, Course, User …)
```

```java
// BaseEntity.java
@MappedSuperclass
public class BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

// AuditableEntity.java
@MappedSuperclass
public class AuditableEntity extends BaseEntity {
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
```

---

## 3. Lesson 02 — Student CRUD

### 3.1 What this lesson covers

- Designing the `Student` entity
- Repository → Service → Controller pattern
- DTO pattern (request/response separation)
- Testing with Swagger UI or Postman

### 3.2 Student Entity

```java
@Entity @Table(name = "students") @Data
public class Student extends AuditableEntity {
    private String studentCode;    // unique, max 20 chars
    private String fullName;       // required
    private LocalDate dateOfBirth;
    private Gender gender;         // enum: MALE, FEMALE, OTHER
    private String gradeLevel;
    private String schoolName;
    private String phone;
    private String description;
    private StudentStatus status;  // enum: ACTIVE, INACTIVE, SUSPENDED
    private BigDecimal latestScore;

    @ManyToOne
    private Parent parent;         // linked after lesson 04
}
```

### 3.3 Repository

```java
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFullNameContainingIgnoreCase(String keyword);
}
```

### 3.4 Service Layer

```java
@Service @RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public List<StudentResponse> findByAll() { ... }
    public Optional<StudentResponse> findById(Long id) { ... }
    public List<StudentResponse> searchByFullName(String keyword) { ... }
    public StudentResponse create(StudentUpsertRequest request) { ... }
    public StudentResponse update(Long id, StudentUpsertRequest request) { ... }
    public void delete(Long id) { ... }
}
```

### 3.5 REST Controller

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/api/students` | List all students |
| GET | `/api/students/{id}` | Get by ID |
| GET | `/api/students/search?keyword=` | Search by name |
| POST | `/api/students` | Create student |
| PUT | `/api/students/{id}` | Update student |
| DELETE | `/api/students/{id}` | Delete student |

Sample request body for **POST /api/students**:

```json
{
  "studentCode": "STU001",
  "fullName": "Nguyen Van A",
  "dateOfBirth": "2010-05-15",
  "gender": "MALE",
  "gradeLevel": "Grade 5",
  "schoolName": "ABC Primary School",
  "phone": "0901234567"
}
```

### 3.6 DTO Pattern Explained

```
Client  ──POST JSON──►  StudentUpsertRequest (DTO)
                              │ modelMapper.map()
                              ▼
                         Student (Entity)  ──► saved to DB
                              │ modelMapper.map()
                              ▼
Client  ◄── JSON ───  StudentResponse (DTO)
```

> **Why DTOs?** They separate your database model from your API contract — you can hide sensitive fields, add computed fields, and version your API independently.

---

## 4. Lesson 03 — Validation & Error Handling

### 4.1 What this lesson covers

- `@Valid` + Bean Validation annotations
- Custom exception classes
- `@RestControllerAdvice` global error handler
- Consistent `ApiResponse<T>` wrapper

### 4.2 Bean Validation on DTOs

```java
public class StudentUpsertRequest {
    @NotBlank(message = "Student code is required")
    @Size(max = 20, message = "Student code must be at most 20 characters")
    private String studentCode;

    @NotBlank(message = "Full name is required")
    private String fullName;

    // ...
}
```

Enable validation on the controller:
```java
@PostMapping
public ResponseEntity<StudentResponse> createStudent(@RequestBody @Valid StudentUpsertRequest request) { ... }
```

### 4.3 Custom Exceptions

```java
// 404 Not Found
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) { super(message); }
}

// 400 Bad Request
public class BadRequestException extends RuntimeException { ... }

// 409 Conflict
public class ConflictException extends RuntimeException { ... }
```

### 4.4 Global Exception Handler

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(404).body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        // collects all field errors into a map: { "fieldName": "error message" }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadCredentials(...) { ... }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneral(...) { ... }
}
```

### 4.5 ApiResponse Wrapper

```java
// Every API response uses this record
public record ApiResponse<T>(boolean success, String message, T data, LocalDateTime timestamp) {
    public static <T> ApiResponse<T> success(String message, T data) { ... }
    public static ApiResponse<Void> error(String message) { ... }
}
```

Sample error response (validation failure):
```json
{
  "success": false,
  "message": "Validation failed",
  "errors": {
    "studentCode": "Student code is required",
    "fullName": "Full name is required"
  }
}
```

---

## 5. Lesson 04 — System Categories

### 5.1 What this lesson covers

- Building catalog entities: **Course**, **Room**, **Teacher**, **ScheduleSlot**, **Parent**
- Full CRUD for each catalog
- Reusing the same Controller/Service/Repository pattern

### 5.2 Entities Overview

#### Course
```java
@Entity @Table(name = "courses")
public class Course extends AuditableEntity {
    private String courseCode;    // e.g. "MATH-5"
    private String name;
    private String description;
    private double tuitionFee;
    private int totalSessions;
    private boolean isActive;
}
```

#### Room
```java
@Entity @Table(name = "rooms")
public class Room extends AuditableEntity {
    private String roomCode;
    private String name;
    private int capacity;
    private boolean isActive;
}
```

#### Teacher
```java
@Entity @Table(name = "teachers")
public class Teacher extends AuditableEntity {
    private String teacherCode;
    private String fullName;
    private TeacherRole role;     // MAIN_TEACHER, ASSISTANT
    private String phone;
    private String email;
    private boolean isActive;
}
```

#### ScheduleSlot
```java
@Entity @Table(name = "schedule_slots")
public class ScheduleSlot extends AuditableEntity {
    private String name;          // e.g. "Mon-Wed-Fri 08:00"
    private String daysOfWeek;    // "MON,WED,FRI"
    private LocalTime startTime;
    private LocalTime endTime;
}
```

#### Parent
```java
@Entity @Table(name = "parents")
public class Parent extends AuditableEntity {
    private String fullName;
    private String phone;
    private String email;
    private String address;
}
```

### 5.3 API Endpoints per Catalog

Each catalog exposes the same shape:

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/courses` | List all |
| GET | `/api/courses/{id}` | Get by ID |
| POST | `/api/courses` | Create |
| PUT | `/api/courses/{id}` | Update |
| DELETE | `/api/courses/{id}` | Delete |

Same pattern applies for `/api/rooms`, `/api/teachers`, `/api/schedule-slots`, `/api/parents`.

---

## 6. Lesson 05 — Login & Role-Based Authorization (JWT)

### 6.1 What this lesson covers

- User entity with roles
- BCrypt password hashing
- JWT access token + refresh token flow
- `SecurityConfig` with stateless sessions
- `@PreAuthorize` method-level security

### 6.2 User Entity & Roles

```java
@Entity @Table(name = "users")
public class User extends AuditableEntity {
    private String username;        // unique
    private String passwordHash;    // BCrypt
    private String fullName;
    private UserRole role;          // ADMIN, ACADEMIC_STAFF, CASHIER, TEACHER, PARENT
    private Boolean isActive = true;

    @ManyToOne private Parent parent;     // set when role = PARENT
    @ManyToOne private Teacher teacher;   // set when role = TEACHER
}
```

Available roles (`UserRole` enum): `ADMIN`, `ACADEMIC_STAFF`, `CASHIER`, `TEACHER`, `PARENT`

### 6.3 JWT Configuration (`application-dev.yaml`)

```yaml
app:
  jwt:
    issuer: yoedu-demo
    secret: qv!!a?6ct7tM;2{:tkJ*vvP(4P7f}%PZ   # change in production!
    access-token-ttl-minutes: 480   # 8 hours
    refresh-token-ttl-days: 7
```

Bound via:
```java
@ConfigurationProperties(prefix = "app.jwt")
public record AppJwtProperties(String issuer, String secret,
                               int accessTokenTtlMinutes, int refreshTokenTtlDays) {}
```

### 6.4 JWT Token Flow

```
1. Client sends POST /api/auth/login  { username, password }
         │
         ▼
2. Server validates credentials → BCryptPasswordEncoder.matches()
         │
         ▼
3. Server generates:
   • accessToken  (TTL: 8 h)  — carries username, roles, userId…
   • refreshToken (TTL: 7 d)  — JTI stored in DB (RefreshTokenSession)
         │
         ▼
4. Client stores tokens, sends accessToken in header:
   Authorization: Bearer <accessToken>
         │
         ▼
5. JwtAuthenticationFilter validates token on every request
         │
         ▼
6. When accessToken expires → POST /api/auth/refresh { refreshToken }
```

### 6.5 Security Configuration

```java
@Configuration @EnableWebSecurity @EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
            JwtAuthenticationFilter jwtFilter) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(POST, "/api/auth/login").permitAll()
                .requestMatchers(POST, "/api/auth/refresh").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs*/**").permitAll()
                .anyRequest().authenticated())
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

### 6.6 Auth API Endpoints

| Method | URL | Auth Required | Description |
|--------|-----|---------------|-------------|
| POST | `/api/auth/login` | No | Login, get tokens |
| POST | `/api/auth/refresh` | No | Refresh access token |
| GET | `/api/auth/me` | Yes | Get current user info |
| POST | `/api/auth/change-password` | Yes | Change own password |

Sample login request:
```json
POST /api/auth/login
{
  "username": "admin",
  "password": "Admin@123"
}
```

Sample response:
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "accessToken": "eyJ...",
    "refreshToken": "eyJ...",
    "tokenType": "Bearer"
  }
}
```

### 6.7 Method-Level Security

```java
@PostMapping
@PreAuthorize("hasAnyRole('ADMIN','ACADEMIC_STAFF')")
public ApiResponse<AttendanceResponse> create(...) { ... }
```

> 💡 `@EnableMethodSecurity` (on `SecurityConfig`) must be present for `@PreAuthorize` to work.

---

## 7. Lesson 06 — Opening a Class (CourseClass)

### 7.1 What this lesson covers

- `CourseClass` entity combining Course + Room + Teacher + ScheduleSlot
- Class lifecycle statuses
- Creating and managing classes

### 7.2 CourseClass Entity

```java
@Entity @Table(name = "course_classes")
public class CourseClass extends AuditableEntity {
    private String classCode;
    private String name;

    @ManyToOne private Course course;
    @ManyToOne private Room room;
    @ManyToOne private ScheduleSlot scheduleSlot;
    @ManyToOne private Teacher teacher;               // main teacher
    @ManyToOne private Teacher assistantTeacher;      // optional

    private LocalDate startDate;
    private LocalDate endDate;
    private int maxStudents;
    private double tuitionFee;                        // may override course fee

    private ClassStatus status; // OPEN, IN_PROGRESS, COMPLETED, CANCELLED
}
```

### 7.3 Class Status Lifecycle

```
OPEN  ──(class starts)──►  IN_PROGRESS  ──(all sessions done)──►  COMPLETED
  │
  └──(cancelled before start)──► CANCELLED
```

### 7.4 API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/api/course-classes?status=OPEN` | List classes (optional filter) |
| POST | `/api/course-classes` | Create a new class |

Sample create request:
```json
POST /api/course-classes
{
  "classCode": "MATH5-2024-01",
  "name": "Math Grade 5 - Morning",
  "courseId": 1,
  "roomId": 2,
  "scheduleSlotId": 3,
  "mainTeacherId": 1,
  "assistantTeacherId": 2,
  "startDate": "2024-09-01",
  "endDate": "2024-12-31",
  "maxStudents": 20,
  "tuitionFee": 1500000
}
```

---

## 8. Lesson 07 — Enrollment (Ghi Danh)

### 8.1 What this lesson covers

- Enrolling a student into a `CourseClass`
- Enrollment validations (max capacity, active student, open class)
- Listing enrolled students per class

### 8.2 Enrollment Concept

Enrollment creates a **many-to-many** link between `Student` and `CourseClass`. This is typically modeled as a join entity (e.g., `Enrollment`) or a `@ManyToMany` with a join table.

```
Student  ──────────  Enrollment  ──────────  CourseClass
                       enrollDate
                       status (ACTIVE / DROPPED)
```

### 8.3 Enrollment Validations

Before enrolling a student, check:
1. The `CourseClass.status` must be `OPEN`
2. Current enrolled count < `CourseClass.maxStudents`
3. `Student.status` must be `ACTIVE`
4. Student is not already enrolled in this class

### 8.4 Typical API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| POST | `/api/enrollments` | Enroll student in a class |
| GET | `/api/course-classes/{classId}/students` | List enrolled students |
| DELETE | `/api/enrollments/{id}` | Drop a student from class |

---

## 9. Lesson 08 — Attendance & Notifications

### 9.1 What this lesson covers

- Recording daily attendance per class session
- `AttendanceStatus` values
- Sending notifications to parents
- Role-based access control on attendance endpoints

### 9.2 Attendance Entity

```java
@Entity @Table(name = "attendances")
public class Attendance extends AuditableEntity {
    @ManyToOne private CourseClass courseClass;
    @ManyToOne private Student student;
    private LocalDate attendanceDate;
    private AttendanceStatus status;  // PRESENT, ABSENT, LATE, EXCUSED
    private String note;
    @ManyToOne private User recordedByUser;  // who took attendance
}
```

### 9.3 Notification Entity

```java
@Entity @Table(name = "notifications")
public class Notification extends AuditableEntity {
    private String title;
    private String content;
    private NotificationType type;              // ABSENCE, GENERAL, ...
    private NotificationRecipientType recipientType;  // PARENT, TEACHER, ALL
    @ManyToOne private Student student;
    @ManyToOne private User sender;
    private boolean isRead;
}
```

### 9.4 API Endpoints

| Method | URL | Roles | Description |
|--------|-----|-------|-------------|
| POST | `/api/attendances` | ADMIN, ACADEMIC_STAFF | Record attendance |
| GET | `/api/attendances/class/{classId}` | ADMIN, ACADEMIC_STAFF | List by class |

Sample create attendance:
```json
POST /api/attendances
Authorization: Bearer <token>
{
  "courseClassId": 1,
  "studentId": 5,
  "attendanceDate": "2024-10-01",
  "status": "ABSENT",
  "note": "No notification from parent"
}
```

> 💡 When a student is marked `ABSENT`, the system can automatically create a `Notification` record for the parent.

---

## 10. Lesson 09 — Monthly Learning Results

### 10.1 What this lesson covers

- Recording monthly academic scores per student per class
- Unique constraint: one result per (student, class, month)
- Teacher comments
- Access control: teachers create, parents read

### 10.2 LearningResult Entity

```java
@Entity
@Table(name = "learning_results",
       uniqueConstraints = @UniqueConstraint(
           columnNames = {"student_id", "course_class_id", "result_month"}))
public class LearningResult extends AuditableEntity {
    @ManyToOne private Student student;
    @ManyToOne private CourseClass courseClass;
    private LocalDate resultMonth;         // first day of the month, e.g. 2024-10-01
    private BigDecimal score;              // 0.00 – 10.00
    private String teacherComment;
    @ManyToOne private User createdByUser;
}
```

### 10.3 API Endpoints

| Method | URL | Roles | Description |
|--------|-----|-------|-------------|
| POST | `/api/learning-results` | ADMIN, ACADEMIC_STAFF | Create result |
| GET | `/api/learning-results/student/{studentId}` | ADMIN, ACADEMIC_STAFF, PARENT | Get results for student |

Sample create result:
```json
POST /api/learning-results
Authorization: Bearer <token>
{
  "studentId": 5,
  "courseClassId": 1,
  "resultMonth": "2024-10-01",
  "score": 8.5,
  "teacherComment": "Good progress this month!"
}
```

---

## 11. Lesson 10 — Tuition Fees & Promotions

### 11.1 What this lesson covers

- `Promotion` entity with fixed or percentage discount
- Linking promotions to invoices
- Business logic for discount calculation

### 11.2 Promotion Entity

```java
@Entity @Table(name = "promotions")
public class Promotion extends AuditableEntity {
    private String promoCode;        // unique
    private String name;
    private DiscountType discountType;  // PERCENTAGE or FIXED_AMOUNT
    private float discountValue;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
    private String note;
}
```

### 11.3 Discount Calculation Logic

```
PERCENTAGE:    finalAmount = originalAmount × (1 - discountValue / 100)
FIXED_AMOUNT:  finalAmount = originalAmount - discountValue  (min 0)
```

### 11.4 Tuition Invoice Entity

```java
@Entity @Table(name = "tuition_invoices")
public class TuitionInvoice extends AuditableEntity {
    private String invoiceCode;          // unique, e.g. "INV-2024-001"
    @ManyToOne private Student student;
    @ManyToOne private CourseClass courseClass;
    private LocalDate billingMonth;
    private float originalAmount;
    private float discountAmount;
    private float finalAmount;
    private float amountPaid;
    private float balanceAmount;         // finalAmount - amountPaid
    private InvoiceStatus status;        // UNPAID, PARTIAL, PAID, OVERDUE
    @ManyToOne private Promotion promotion;
    private LocalDate dueDate;
    private String note;
}
```

---

## 12. Lesson 11 — Payment & Debt Tracking

### 12.1 What this lesson covers

- Creating tuition invoices via the Billing API
- Recording payments against invoices
- Updating `amountPaid`, `balanceAmount`, and `InvoiceStatus`
- Cashier role access control

### 12.2 Invoice Status Lifecycle

```
UNPAID  ──(partial payment)──►  PARTIAL  ──(full payment)──►  PAID
  │
  └──(past due date, not fully paid)──►  OVERDUE
```

### 12.3 Billing API Endpoints

| Method | URL | Roles | Description |
|--------|-----|-------|-------------|
| POST | `/api/billing/invoices` | ADMIN, ACADEMIC_STAFF, CASHIER | Create invoice |
| GET | `/api/billing/students/{studentId}/invoices` | ADMIN, ACADEMIC_STAFF, CASHIER, PARENT | List invoices |

Sample create invoice:
```json
POST /api/billing/invoices
Authorization: Bearer <token>
{
  "studentId": 5,
  "courseClassId": 1,
  "billingMonth": "2024-10-01",
  "promoCode": "SUMMER10",
  "dueDate": "2024-10-31",
  "note": "October tuition"
}
```

Sample response:
```json
{
  "success": true,
  "message": "Invoice created",
  "data": {
    "invoiceCode": "INV-2024-001",
    "originalAmount": 1500000,
    "discountAmount": 150000,
    "finalAmount": 1350000,
    "amountPaid": 0,
    "balanceAmount": 1350000,
    "status": "UNPAID"
  }
}
```

---

## 13. Lesson 12 — Parent Portal & Backend Summary

### 13.1 What this lesson covers

- Parent-facing endpoints (read-only, scoped to own children)
- Data access control: parents see only their children's data
- Consolidating all features into a coherent backend

### 13.2 Parent Data Access Rules

When a user has `role = PARENT`:
- The `User.parent` field links them to a `Parent` record
- `Parent` → `Student` (one parent → many students)
- A parent can only access learning results, attendance, and invoices **for their own students**

The service layer enforces this:
```java
// Example: learning result service
public List<LearningResultResponse> findByStudentId(Long studentId, String username) {
    User user = userRepository.findByUsername(username).orElseThrow(...);
    if (user.getRole() == UserRole.PARENT) {
        // verify that studentId belongs to this parent
        boolean owns = user.getParent().getStudents().stream()
            .anyMatch(s -> s.getId().equals(studentId));
        if (!owns) throw new AccessDeniedException("Access denied");
    }
    return learningResultRepository.findByStudentId(studentId)
        .stream().map(this::toResponse).toList();
}
```

### 13.3 Full Roles & Permissions Summary

| Endpoint | ADMIN | ACADEMIC_STAFF | CASHIER | TEACHER | PARENT |
|----------|-------|---------------|---------|---------|--------|
| Student CRUD | ✅ | ✅ | — | — | — |
| Course / Room / Teacher CRUD | ✅ | ✅ | — | — | — |
| Open Class | ✅ | ✅ | — | — | — |
| Enrollment | ✅ | ✅ | — | — | — |
| Attendance (write) | ✅ | ✅ | — | — | — |
| Attendance (read) | ✅ | ✅ | — | — | — |
| Learning Results (write) | ✅ | ✅ | — | — | — |
| Learning Results (read) | ✅ | ✅ | — | ✅ (own class) | ✅ (own child) |
| Create Invoice | ✅ | ✅ | ✅ | — | — |
| View Invoices | ✅ | ✅ | ✅ | — | ✅ (own child) |

---

## 14. Project Structure Reference

```
yoot_management/
├── docker-compose.yaml               # MySQL dev database
├── pom.xml                           # Maven build & dependencies
└── src/main/java/duy/project/yoot_management/
    ├── YootManagementApplication.java  # @SpringBootApplication entry point
    │
    ├── common/
    │   ├── ApiResponse.java            # Generic API wrapper record
    │   ├── exception/
    │   │   ├── GlobalExceptionHandler.java  # @RestControllerAdvice
    │   │   ├── NotFoundException.java
    │   │   ├── BadRequestException.java
    │   │   └── ConflictException.java
    │   └── validations/                # Custom validators (if any)
    │
    ├── config/
    │   ├── AppConfig.java              # ModelMapper bean etc.
    │   ├── AppJwtProperties.java       # @ConfigurationProperties
    │   └── SecurityConfig.java         # Spring Security + JWT filter chain
    │
    ├── domains/                        # JPA entities
    │   ├── enums/                      # AttendanceStatus, ClassStatus, Gender …
    │   ├── BaseEntity.java
    │   ├── AuditableEntity.java
    │   ├── User.java
    │   ├── Student.java
    │   ├── Parent.java
    │   ├── Teacher.java
    │   ├── Course.java
    │   ├── Room.java
    │   ├── ScheduleSlot.java
    │   ├── CourseClass.java
    │   ├── Attendance.java
    │   ├── LearningResult.java
    │   ├── Notification.java
    │   ├── Promotion.java
    │   ├── TuitionInvoice.java
    │   └── RefreshTokenSession.java
    │
    ├── repository/                     # Spring Data JPA repositories
    ├── service/                        # Business logic services
    │   └── impl/                       # Service implementations
    ├── dto/                            # Request/Response DTOs grouped by feature
    │   ├── auth/
    │   ├── student/
    │   ├── course/
    │   ├── course_class/
    │   ├── atteance/
    │   ├── learning_result/
    │   └── billing/
    ├── controller/                     # REST controllers
    └── security/
        ├── JwtService.java             # Token generation & parsing
        └── JwtAuthenticationFilter.java # Servlet filter
```

---

## 15. API Quick Reference

Base URL: `http://localhost:8080`
Swagger UI: `http://localhost:8080/swagger-ui.html`

### Authentication

```
POST   /api/auth/login
POST   /api/auth/refresh
GET    /api/auth/me
POST   /api/auth/change-password
```

### Students

```
GET    /api/students
GET    /api/students/{id}
GET    /api/students/search?keyword=
POST   /api/students
PUT    /api/students/{id}
DELETE /api/students/{id}
```

### System Catalogs

```
GET/POST/PUT/DELETE  /api/courses
GET/POST/PUT/DELETE  /api/rooms
GET/POST/PUT/DELETE  /api/teachers
GET/POST/PUT/DELETE  /api/schedule-slots
GET/POST/PUT/DELETE  /api/parents
```

### Classes & Enrollment

```
GET    /api/course-classes?status=
POST   /api/course-classes
POST   /api/enrollments
GET    /api/course-classes/{classId}/students
```

### Attendance

```
POST   /api/attendances
GET    /api/attendances/class/{classId}
```

### Learning Results

```
POST   /api/learning-results
GET    /api/learning-results/student/{studentId}
```

### Billing

```
POST   /api/billing/invoices
GET    /api/billing/students/{studentId}/invoices
```

---

## Tips for Beginners

1. **Always start the database first** — `docker compose up -d` before running the app.
2. **Use Swagger UI** at `/swagger-ui.html` to explore and test APIs without Postman.
3. **Read error messages carefully** — `GlobalExceptionHandler` formats every error clearly.
4. **Follow the layer order** when adding a new feature:
   `Entity → Repository → Service → DTO → Controller`
5. **Check `ddl-auto: update`** — Hibernate auto-creates tables on first run; no manual SQL needed during development.
6. **Commit `application-dev.yaml`** cautiously — it contains a JWT secret. In production, use environment variables instead.
7. **JWT tokens expire** — if you get `401 Unauthorized`, call `/api/auth/refresh` or log in again.
8. **`@PreAuthorize` roles** — Spring Security prefixes role names with `ROLE_` internally; write `hasRole('ADMIN')` or `hasAnyRole('ADMIN','CASHIER')` without the prefix in your code.

---

*Generated from source code and course materials — YOOT Management System, Spring Boot 4 course.*

