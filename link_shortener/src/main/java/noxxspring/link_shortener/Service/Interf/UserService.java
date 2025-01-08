package noxxspring.link_shortener.Service.Interf;

import noxxspring.link_shortener.Dto.JwtAuthResponse;
import noxxspring.link_shortener.Dto.LoginRequest;
import noxxspring.link_shortener.Model.User;

public interface UserService {
    public User registerUser (User user);

    public JwtAuthResponse loginUser (LoginRequest loginRequest);
}
