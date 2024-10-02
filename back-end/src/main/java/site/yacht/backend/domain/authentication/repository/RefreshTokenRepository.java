package site.yacht.backend.domain.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.yacht.backend.domain.authentication.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    boolean existsByUserEmail(String userEmail);

    Optional<RefreshToken> findByUserEmail(String userEmail);

    void deleteByUserEmail(String userEmail);
}
