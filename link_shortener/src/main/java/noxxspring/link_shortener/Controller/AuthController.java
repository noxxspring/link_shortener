package noxxspring.link_shortener.Controller;

import lombok.RequiredArgsConstructor;
import noxxspring.link_shortener.Dto.JwtAuthResponse;
import noxxspring.link_shortener.Dto.LoginRequest;
import noxxspring.link_shortener.Dto.RegisterRequest;
import noxxspring.link_shortener.Model.User;
import noxxspring.link_shortener.Service.Interf.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setEmail(registerRequest.getEmail());
        user.setRole("ROLE_USER");
        userService.registerUser(user);
        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> loginUser (@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(userService.loginUser(loginRequest));
    }
}
