package duy.project.yoot_management.domains;

import duy.project.yoot_management.domains.enums.TeacherRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "teachers")
@Data
public class Teacher extends AuditableEntity {

    @Column(name = "teacher_code", columnDefinition = "varchar(20)", nullable = false, unique = true)
    private String teacherCode;
    @Column(name = "full_name", columnDefinition = "varchar(100)", nullable = false)
    private String fullName;
    @Column(unique = true, nullable = false)
    private String phone;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(name = "teacher_role", nullable = false)
    private TeacherRole teacherRole = TeacherRole.TEACHER;
    @Column(name = "cccd_image_url", columnDefinition = "varchar(255)")
    private String cccdImageUrl;
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

}
