package duy.project.yoot_management.domains;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Entity
@Table(name = "refresh_token_sessions")
@Data
public class RefreshTokenSession extends AuditableEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String jti;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @Column(name = "revoked_at")
    private Instant revokedAt;

    @Column(name = "replaced_by_jti", length = 100)
    private String replacedByJti;

}
