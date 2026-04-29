package duy.project.yoot_management.dto.request;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class CourseRequest {

    private String courseCode;

    private String name;

    private String description;

    private double tuitionFee;

    private int totalSessions;

    private boolean isActive;

}
