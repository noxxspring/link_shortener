package noxxspring.link_shortener.Repository;

import noxxspring.link_shortener.Model.UrlMapping;
import noxxspring.link_shortener.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping,Long> {
    UrlMapping findByShortUrl (String shortUrl);
    List<UrlMapping> findByUser (User user);
}
