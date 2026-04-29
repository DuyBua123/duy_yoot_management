package duy.project.yoot_management.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "schedule_slots")
public class ScheduleSlot extends AuditableEntity {

    @Column(name = "slot_code", columnDefinition = "varchar(20)", unique = true, nullable = false)
    private String slotCode;
    private String note;

    private int weekday;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

}
