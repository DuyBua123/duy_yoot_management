package duy.project.yoot_management.dto.schedule_slot;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class ScheduleSlotResponse {

    private Long id;

    private String slotCode;

    private String note;

    private int weekday;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

