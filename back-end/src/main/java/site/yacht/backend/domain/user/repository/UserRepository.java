package site.yacht.backend.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.yacht.backend.domain.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    List<User> findByEmailIn(List<String> emails);
}
