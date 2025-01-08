package noxxspring.link_shortener.Security;

import noxxspring.link_shortener.Model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class AuthUser implements UserDetails {

    private  static final Long serialVersionUiD = 1L;
    private Long id;
    private String username;
    private String email;
    private String password;
    Collection<? extends GrantedAuthority> authorities;

    public AuthUser(Long id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    // builder method
    public static AuthUser build(User user){
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        return new AuthUser(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(authority)
        );
    }





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
