package duy.project.yoot_management.dto.request;

import duy.project.yoot_management.domains.enums.TeacherRole;
import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class TeacherRequest {

    private String teacherCode;
    private String fullName;
    private String phone;
    private String email;
    private String cccdImageUrl;
    private TeacherRole teacherRole;
    private boolean isActive;

}
