package duy.project.yoot_management.dto.student;


import duy.project.yoot_management.domains.enums.Gender;
import duy.project.yoot_management.domains.enums.StudentStatus;
import duy.project.yoot_management.dto.parent.ParentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private Long id;

    private String studentCode;

    private String fullName;

    private LocalDate dateOfBirth;

    private Gender gender = Gender.OTHER;

    private String gradeLevel;

    private String schoolName;

    private String phone;

    private ParentResponse parent;

    private StudentStatus status = StudentStatus.ACTIVE;

    private BigDecimal latestScore = BigDecimal.ZERO;

    private String note;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
