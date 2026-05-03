package duy.project.yoot_management.dto.response;

import duy.project.yoot_management.domains.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentResponse {

    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private String relationship;
    private Gender gender;

}
