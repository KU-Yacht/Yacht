package site.yacht.backend.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import site.yacht.backend.domain.user.domain.User;
import site.yacht.backend.domain.user.exception.UserNotFoundException;
import site.yacht.backend.domain.user.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(UserNotFoundException::new);

        return new UserDetailsImpl(user);
    }
}
