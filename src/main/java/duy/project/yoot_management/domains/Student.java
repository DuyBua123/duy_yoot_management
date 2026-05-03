package duy.project.yoot_management.domains;

import duy.project.yoot_management.domains.enums.Gender;
import duy.project.yoot_management.domains.enums.StudentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "students")
@Data
public class Student extends AuditableEntity {

    @Column(name = "student_code", nullable = false, unique = true, length = 20)
    private String studentCode;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Gender gender = Gender.OTHER;

    @Column(name = "grade_level", length = 30)
    private String gradeLevel;

    @Column(name = "school_name", length = 100)
    private String schoolName;

    @Column(length = 20)
    private String phone;

    @Column(length = 255)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StudentStatus status = StudentStatus.ACTIVE;

    @Column(name = "latest_score", precision = 5, scale = 2)
    private BigDecimal latestScore = BigDecimal.ZERO;

    @Column(length = 255)
    private String note;

}
