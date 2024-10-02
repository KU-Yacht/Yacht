package site.yacht.backend.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.yacht.backend.domain.user.domain.User;
import site.yacht.backend.domain.user.exception.UserEmailDuplicationException;
import site.yacht.backend.domain.user.exception.UserNicknameDuplicationException;
import site.yacht.backend.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserRegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(String email, String password, String nickname) {
        validateDuplicateEmail(email);
        validateDuplicateNickName(nickname);

        String encryptedPassword = passwordEncoder.encode(password);
        User user = new User(email, encryptedPassword, nickname);
        userRepository.save(user);

        // TODO Create default Project
    }

    private void validateDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserEmailDuplicationException();
        }
    }

    private void validateDuplicateNickName(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new UserNicknameDuplicationException();
        }
    }

}
