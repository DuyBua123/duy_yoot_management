package duy.project.yoot_management.dto.response;

import duy.project.yoot_management.domains.Course;
import duy.project.yoot_management.domains.Room;
import duy.project.yoot_management.domains.ScheduleSlot;
import duy.project.yoot_management.domains.Teacher;
import duy.project.yoot_management.domains.enums.ClassStatus;
import duy.project.yoot_management.dto.request.CourseResponse;
import jakarta.persistence.*;

import java.time.LocalDate;

public record CourseClassResponse(
        Long id,
    String codeCode,

    String name,

    CourseResponse course,

    Room room,
    ScheduleSlot scheduleSlot,
    Teacher teacher,
    Teacher assistantTeacher;

private LocalDate startDate;
private LocalDate endDate;

private int maxStudents;

@Column(columnDefinition = "decimal")
private double tuitionFee;

@Enumerated(EnumType.STRING)
@Column(nullable = false, length = 20)
private ClassStatus status = ClassStatus.OPEN;
) {
}
