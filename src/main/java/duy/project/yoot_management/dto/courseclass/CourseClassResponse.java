package duy.project.yoot_management.dto.courseclass;


import duy.project.yoot_management.domains.ScheduleSlot;
import duy.project.yoot_management.domains.Teacher;
import duy.project.yoot_management.domains.enums.ClassStatus;
import duy.project.yoot_management.dto.course.CourseResponse;
import duy.project.yoot_management.dto.room.RoomResponse;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseClassResponse {

    private Long id;

    private String codeCode;

    private String name;

    private CourseResponse course;

    private RoomResponse room;

    private ScheduleSlot slot;

    private Teacher mainTeacher;

    private Teacher assistantTeacher;

    private LocalDate startDate;
    private LocalDate endDate;

    private int maxStudents;

    private double tuitionFee;

    private ClassStatus status;
}
