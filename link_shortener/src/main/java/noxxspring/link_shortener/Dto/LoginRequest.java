package noxxspring.link_shortener.Dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
