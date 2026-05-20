package duy.project.yoot_management.dto.parent;

import com.yo.day1.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentResponse {

    private Long id;

    private String fullName;

    private String phone;

    private String email;

    private String address;

    private Gender gender = Gender.OTHER;

    private String relationship;
}
