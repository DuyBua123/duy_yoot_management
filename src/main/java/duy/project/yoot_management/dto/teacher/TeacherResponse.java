package duy.project.yoot_management.dto.teacher;

import duy.project.yoot_management.domains.enums.TeacherRole;
import lombok.Data;

@Data
public class TeacherResponse {

    private Long id;

    private String teacherCode;

    private String fullName;

    private String phone;

    private String email;

    private TeacherRole teacherRole;

    private boolean isActive;

}

