package duy.project.yoot_management.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "parents")
@Data
public class Parent extends AuditableEntity {

    @Column(name = "full_name", columnDefinition = "varchar(100)", nullable = false)
    private String fullName;
    @Column(unique = true, nullable = false)
    private String phone;
    @Column(unique = true)
    private String email;
    @Column(columnDefinition = "varchar(255)")
    private String address;

}
