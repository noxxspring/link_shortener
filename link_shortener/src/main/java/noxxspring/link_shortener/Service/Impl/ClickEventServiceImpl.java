package noxxspring.link_shortener.Service.Impl;

import lombok.RequiredArgsConstructor;
import noxxspring.link_shortener.Dto.ClickEventDto;
import noxxspring.link_shortener.Model.ClickEvent;
import noxxspring.link_shortener.Model.UrlMapping;
import noxxspring.link_shortener.Model.User;
import noxxspring.link_shortener.Repository.ClickEventRepository;
import noxxspring.link_shortener.Repository.UrlMappingRepository;
import noxxspring.link_shortener.Service.Interf.ClickEventService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClickEventServiceImpl implements ClickEventService {
    private final ClickEventRepository clickEventRepository;
    private final UrlMappingRepository urlMappingRepository;

    @Override
    public List<ClickEventDto> getClickEventByDate(String shortUrl, LocalDateTime startDate, LocalDateTime endDate) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
        if(urlMapping != null){
            return clickEventRepository.findByUrlMappingAndClickDateBefore(urlMapping,
                    startDate,endDate).stream().collect(Collectors.groupingBy(click ->
                    click.getClickDate().toLocalDate(),Collectors.counting()))
                    .entrySet().stream()
                    .map(entry -> {
                        ClickEventDto clickEventDto = new ClickEventDto();
                        clickEventDto.setClickDate(entry.getKey().atStartOfDay());
                        clickEventDto.setCount(entry.getValue());
                        return clickEventDto;
                    })
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    @Override
    public Map<LocalDate, Long> getTotalClicksByUserAndDate(User user, LocalDate startDate, LocalDate endDate) {
        List<UrlMapping> urlMappings = urlMappingRepository.findByUser(user);
        List<ClickEvent> clickEvents = clickEventRepository.findByUrlMappingInAndClickDateBetween(
                urlMappings,startDate.atStartOfDay(),endDate.plusDays(1).atStartOfDay()
        );
        return clickEvents.stream()
                .collect(Collectors.groupingBy(click -> click.getClickDate().toLocalDate()
                        ,Collectors.counting()));
    }
}
