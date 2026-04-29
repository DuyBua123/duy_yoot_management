package duy.project.yoot_management.domains;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student extends AuditableEntity {

    private String fullName;
    private String email;
    private String phone;

}
