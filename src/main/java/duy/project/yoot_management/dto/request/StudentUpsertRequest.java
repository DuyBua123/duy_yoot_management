package duy.project.yoot_management.dto.request;

import duy.project.yoot_management.common.validations.NotFutureDateConstraint;
import duy.project.yoot_management.domains.Parent;
import duy.project.yoot_management.domains.enums.Gender;
import duy.project.yoot_management.domains.enums.StudentStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpsertRequest {

    @Size(min = 2)
    private String studentCode;

    @NotBlank
    private String fullName;

    @NotFutureDateConstraint
    private LocalDate dateOfBirth;

    private Gender gender = Gender.OTHER;

    @Size(min = 1, max = 30)
    private String gradeLevel;

    private String schoolName;

    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})", message = "Invalid phone number format")
    private String phone;

    private Long parentId;

    private StudentStatus status = StudentStatus.ACTIVE;

    @Min(value = 0)
    @Max(value = 10)
    private BigDecimal latestScore = BigDecimal.ZERO;

    private String note;

}
