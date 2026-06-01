package duy.project.yoot_management.dto.parent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationCard {
    private Long id;
    private String type;
    private String title;
    private String content;
    private Boolean isRead;
    private LocalDateTime createdAt;
}