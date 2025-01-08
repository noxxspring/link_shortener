package noxxspring.link_shortener.Security;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import noxxspring.link_shortener.Model.User;
import noxxspring.link_shortener.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("User not found"+ username));
        return AuthUser.build(user);
    }
}
