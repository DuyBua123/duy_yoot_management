package duy.project.yoot_management.domains;

import duy.project.yoot_management.domains.enums.ClassStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "course_classes")
@Data
public class CourseClass extends AuditableEntity {

    private String codeCode;

    @Column(columnDefinition = "varchar(100)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "schedule_slot_id", nullable = false)
    private ScheduleSlot scheduleSlot;

    @ManyToOne
    @JoinColumn(name = "main_teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "assistant_teacher_id")
    private Teacher assistantTeacher;

    private LocalDate startDate;
    private LocalDate endDate;

    private int maxStudents;

    @Column(columnDefinition = "decimal")
    private double tuitionFee;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ClassStatus status = ClassStatus.OPEN;

}
