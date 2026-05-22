package duy.project.yoot_management.dto.teacher;

import duy.project.yoot_management.domains.enums.TeacherRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class TeacherUpsertRequest {

    @NotBlank
    private String teacherCode;
    @NotBlank
    private String fullName;
    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})", message = "Invalid phone number format")
    private String phone;
    @Email
    private String email;
    @NotBlank
    private String cccdImageUrl;
    private TeacherRole teacherRole = TeacherRole.TEACHER;
    private boolean isActive = true;

}
