package noxxspring.link_shortener.Service.Impl;

import lombok.RequiredArgsConstructor;
import noxxspring.link_shortener.Dto.JwtAuthResponse;
import noxxspring.link_shortener.Dto.LoginRequest;
import noxxspring.link_shortener.Model.User;
import noxxspring.link_shortener.Repository.UserRepository;
import noxxspring.link_shortener.Security.AuthUser;
import noxxspring.link_shortener.Security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements noxxspring.link_shortener.Service.Interf.UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public JwtAuthResponse loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(authUser);
        return new JwtAuthResponse(jwt);

    }

    @Override
    public User findByUsername(String name) {
        return userRepository.findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + name));
    }
}
