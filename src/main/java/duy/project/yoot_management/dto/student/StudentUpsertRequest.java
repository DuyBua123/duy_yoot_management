package duy.project.yoot_management.dto.student;

import duy.project.yoot_management.domains.enums.Gender;
import duy.project.yoot_management.domains.enums.StudentStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentUpsertRequest {

    @Size(min = 2)
    private String studentCode;

    @Size(min = 2)
    private String fullName;

    private LocalDate dateOfBirth;

    @NotNull
    private Gender gender = Gender.OTHER;

    @NotBlank
    private String gradeLevel;

    private String schoolName;

    @Pattern(regexp="^(84|0[35789])+([0-9]{8})$")
    private String phone;

    private Long parentId;

    private StudentStatus status = StudentStatus.ACTIVE;

    @Min(value = 0)
    @Max(value = 10)
    private BigDecimal latestScore = BigDecimal.ZERO;

    private String note;

}
