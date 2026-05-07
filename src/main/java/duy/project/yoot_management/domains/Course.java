package duy.project.yoot_management.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "courses")
@Data // Setters, Getters, toString, equals, hashCode
public class Course extends AuditableEntity {

    @Column(name = "course_code", columnDefinition = "varchar(20)")
    private String courseCode;

    @Column(columnDefinition = "varchar(100)")
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    private double tuitionFee;

    private int totalSessions;

    private boolean isActive;

}
