package noxxspring.link_shortener.Dto;

import lombok.Data;

import java.util.List;


@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private List<String> role;
}
