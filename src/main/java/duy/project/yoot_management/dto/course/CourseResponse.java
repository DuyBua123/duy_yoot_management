package duy.project.yoot_management.dto.course;

import lombok.Data;

@Data
public class CourseResponse {

    private Long id;

    private String courseCode;

    private String name;

    private String description;

    private double tuitionFee;

    private int totalSessions;

    private byte isActive;
}
