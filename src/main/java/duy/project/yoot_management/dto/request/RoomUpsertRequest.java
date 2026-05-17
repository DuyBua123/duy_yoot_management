package duy.project.yoot_management.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RoomUpsertRequest {

    @Length(min = 1, max = 20)
    private String roomCode;

    @Length(max = 100)
    private String name;

    @Min(1)
    private int capacity;

    private String description;

}
