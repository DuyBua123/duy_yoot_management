package duy.project.yoot_management.dto.room;

import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RoomUpsertRequest {

    @Length(min = 1, max = 20)
//    @NotBlank
    private String roomCode;

    @Length(max = 100)
    private String name;

    @Min(1)
    private int capacity;

    private String description;

}
