package noxxspring.link_shortener.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private String message;
    private final LocalDateTime timeStamp = LocalDateTime.now();
    private String token;
    private String role;
    private String expirationTime;

    private UserDto user;
    private List<UserDto> userList;

    private UrlMappingDto urlMapping;
    private List<UrlMappingDto> urlMappingList;

    private ClickEventDto clickEvent;
    private List<ClickEventDto> clickEventList;
}
