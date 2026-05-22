package duy.project.yoot_management.dto.auth;

public record CurrentUserResponse(
        Long id,
        String username,
        String fullName,
        String role,
        Long parentId,
        Long teacherId
) {
}
