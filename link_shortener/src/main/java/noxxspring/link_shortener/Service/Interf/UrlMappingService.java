package noxxspring.link_shortener.Service.Interf;

import noxxspring.link_shortener.Dto.UrlMappingDto;
import noxxspring.link_shortener.Model.User;

import java.util.List;

public interface UrlMappingService {
    public UrlMappingDto createShortUrl(String originalUrl, User user);

    public List<UrlMappingDto> getUsersUrls(User user);
}
