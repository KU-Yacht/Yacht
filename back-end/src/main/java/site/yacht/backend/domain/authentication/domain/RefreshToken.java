package site.yacht.backend.domain.authentication.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "idx_user_email", columnList = "userEmail"))
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String refreshToken;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    public RefreshToken(String userEmail, String refreshToken, LocalDateTime expiredAt) {
        this.userEmail = userEmail;
        this.refreshToken = refreshToken;
        this.expiredAt = expiredAt;
    }

}
