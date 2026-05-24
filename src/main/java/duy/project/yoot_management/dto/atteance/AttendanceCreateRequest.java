package duy.project.yoot_management.dto.atteance;

import duy.project.yoot_management.domains.enums.AttendanceStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AttendanceCreateRequest {

    @NotNull
    private Long courseClassId;
    @NotNull
    private Long studentId;
    @NotNull
    private LocalDate attendanceDate;
    @NotNull
    private AttendanceStatus status;
    @Size(max = 255)
    private String note;

}