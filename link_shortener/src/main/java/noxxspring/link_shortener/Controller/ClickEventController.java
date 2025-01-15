package noxxspring.link_shortener.Controller;

import lombok.RequiredArgsConstructor;
import noxxspring.link_shortener.Dto.ClickEventDto;
import noxxspring.link_shortener.Model.User;
import noxxspring.link_shortener.Service.Interf.ClickEventService;
import noxxspring.link_shortener.Service.Interf.UrlMappingService;
import noxxspring.link_shortener.Service.Interf.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
@RequiredArgsConstructor
public class ClickEventController {

    private final ClickEventService clickEventService;
    private final UserService userService;


    @GetMapping("/analytics{shortUrl}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ClickEventDto>> getUrlAnalytics(@PathVariable String shortUrl,
                                                               @RequestParam("startDate") String startDate,
                                                               @RequestParam("endDate") String endDate){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime start = LocalDateTime.parse(startDate, formatter);
        LocalDateTime end = LocalDateTime.parse(endDate, formatter);
        List<ClickEventDto> clickEventDtos = clickEventService.getClickEventByDate(
                shortUrl,start, end);
        return ResponseEntity.ok(clickEventDtos);
    }

    @GetMapping("totalClicks")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Map<LocalDate, Long>> getTotalClicksByDate(Principal principal,
                                                                     @RequestParam("startDate") String startDate,
                                                                     @RequestParam("endDate") String endDate){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        User user = userService.findByUsername(principal.getName());
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        Map<LocalDate, Long> totalClicks = clickEventService.getTotalClicksByUserAndDate(
                user,start,end);
        return ResponseEntity.ok(totalClicks);

    }
}
