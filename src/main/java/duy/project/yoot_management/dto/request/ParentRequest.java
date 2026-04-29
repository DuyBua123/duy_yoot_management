package duy.project.yoot_management.dto.request;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class ParentRequest {

    private String fullName;
    private String phone;
    private String email;
    private String address;

}
