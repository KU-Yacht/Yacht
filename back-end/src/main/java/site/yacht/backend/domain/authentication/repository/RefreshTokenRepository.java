package site.yacht.backend.domain.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.yacht.backend.domain.authentication.domain.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    boolean existsByUserEmail(String userEmail);

    void deleteByUserEmail(String userEmail);
}
