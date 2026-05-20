package duy.project.yoot_management.dto.course_class;


import duy.project.yoot_management.domains.enums.ClassStatus;
import duy.project.yoot_management.dto.course.CourseResponse;
import duy.project.yoot_management.dto.room.RoomResponse;
import duy.project.yoot_management.dto.schedule_slot.ScheduleSlotResponse;
import duy.project.yoot_management.dto.teacher.TeacherResponse;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseClassResponse {

    private Long id;

    private String classCode;

    private String name;

    private CourseResponse course;

    private RoomResponse room;

    private ScheduleSlotResponse scheduleSlot;

    private TeacherResponse mainTeacher;

    private TeacherResponse assistantTeacher;

    private LocalDate startDate;
    private LocalDate endDate;

    private int maxStudents;

    private double tuitionFee;

    private ClassStatus status;
}
