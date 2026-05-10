package duy.project.yoot_management.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "rooms")
@Data // Setters, Getters, toString, equals, hashCode
public class Room extends AuditableEntity {

    @Column(name = "room_code", columnDefinition = "varchar(20)", unique = true)
    private String roomCode;

    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String name;

    private int capacity;

    private String description;

}
