package noxxspring.link_shortener.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import noxxspring.link_shortener.Model.UrlMapping;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class ClickEventDto {

    private Long id;
    private LocalDateTime createdAt;

    private UrlMapping urlMapping;
}
