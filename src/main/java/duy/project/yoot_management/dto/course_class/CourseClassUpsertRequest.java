package duy.project.yoot_management.dto.course_class;

import duy.project.yoot_management.domains.enums.ClassStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseClassUpsertRequest {


    @NotBlank
    @Size(max = 100)
    String name;

    @NotNull
    Long courseId;

    @NotNull
    Long roomId;

    @NotNull
    Long scheduleSlotId;

    @NotNull
    Long mainTeacherId;

    Long assistantTeacherId;

    @NotNull
    LocalDate startDate;

    LocalDate endDate;

    @NotNull
    @Min(1) Integer maxStudents;

    @NotNull
    @DecimalMin("0.0")
    Double tuitionFee;

    @NotNull
    ClassStatus status;
}
