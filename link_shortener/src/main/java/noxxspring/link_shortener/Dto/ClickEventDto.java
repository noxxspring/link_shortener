package noxxspring.link_shortener.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClickEventDto {

    private LocalDateTime clickDate;
    private Long count;
}
