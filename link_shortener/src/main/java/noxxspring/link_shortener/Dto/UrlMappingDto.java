package noxxspring.link_shortener.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import noxxspring.link_shortener.Model.ClickEvent;
import noxxspring.link_shortener.Model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UrlMappingDto {

    private Long id;
    private String originalUrl;
    private String shortUrl;
    private int clickCount;
    private String username;
    private LocalDateTime createdAt;
}
