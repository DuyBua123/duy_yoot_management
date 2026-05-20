package duy.project.yoot_management.dto.request;

import jakarta.persistence.Column;

public record CourseResponse(
        String courseCode,
        String name,
        String description,
        double tuitionFee,
        int totalSessions,
        boolean isActive
) {
}
