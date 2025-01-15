package noxxspring.link_shortener.Repository;

import noxxspring.link_shortener.Dto.ClickEventDto;
import noxxspring.link_shortener.Model.ClickEvent;
import noxxspring.link_shortener.Model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClickEventRepository extends JpaRepository<ClickEvent, Long> {
    List<ClickEvent> findByUrlMappingAndClickDateBefore (UrlMapping mapping,
                                                            LocalDateTime startDate,
                                                            LocalDateTime endDate);

    List<ClickEvent> findByUrlMappingInAndClickDateBetween (List<UrlMapping> urlMappings,
                                                            LocalDateTime startDate,
                                                            LocalDateTime endDate);
}
