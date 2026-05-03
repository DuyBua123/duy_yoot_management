package duy.project.yoot_management.dto.request;

import duy.project.yoot_management.domains.Parent;
import duy.project.yoot_management.domains.enums.Gender;
import duy.project.yoot_management.domains.enums.StudentStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    private String fullName;

    private LocalDate dateOfBirth;

    private Gender gender = Gender.OTHER;

    @Min(value = 1)
    @Max(value = 4)
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
