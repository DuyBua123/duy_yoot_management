package duy.project.yoot_management.dto.parent;

import duy.project.yoot_management.domains.enums.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCard {
    private Long id;
    private String studentCode;
    private String fullName;
    private String status;
    private BigDecimal latestScore;
}