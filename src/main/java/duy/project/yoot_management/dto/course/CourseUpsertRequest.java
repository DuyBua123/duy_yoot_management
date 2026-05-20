package duy.project.yoot_management.dto.course;

import lombok.Getter;

@Getter
public class CourseUpsertRequest {

    private String courseCode;

    private String name;

    private String description;

    private double tuitionFee;

    private int totalSessions;

    private boolean isActive;

}
