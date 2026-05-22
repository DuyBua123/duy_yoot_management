package duy.project.yoot_management.dto.schedule_slot;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalTime;

@Data
public class ScheduleSlotUpsertRequest {

    @NotBlank
    @Length(min = 1, max = 20)
    private String slotCode;

    private String note;

    @Min(1)
    @Max(7)
    private int weekday;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

}

