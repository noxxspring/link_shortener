package noxxspring.link_shortener.Service.Interf;

import noxxspring.link_shortener.Dto.ClickEventDto;
import noxxspring.link_shortener.Model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ClickEventService {
    List<ClickEventDto> getClickEventByDate (String shortUrl, LocalDateTime startDate,
                                             LocalDateTime endDate);
    public Map<LocalDate, Long> getTotalClicksByUserAndDate(User user, LocalDate startDate,
                                                            LocalDate endDate);
}
