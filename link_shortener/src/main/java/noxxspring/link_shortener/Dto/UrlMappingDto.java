package noxxspring.link_shortener.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import noxxspring.link_shortener.Model.ClickEvent;
import noxxspring.link_shortener.Model.User;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class UrlMappingDto {

    private Long id;
    private String originalUrl;
    private String shortUrl;
    private int clickCount;

    private User user;

    private List<ClickEventDto> clickEventList;
}
