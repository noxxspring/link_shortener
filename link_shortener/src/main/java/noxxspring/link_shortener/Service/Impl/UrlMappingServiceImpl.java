package noxxspring.link_shortener.Service.Impl;

import lombok.RequiredArgsConstructor;
import noxxspring.link_shortener.Dto.UrlMappingDto;
import noxxspring.link_shortener.Model.UrlMapping;
import noxxspring.link_shortener.Model.User;
import noxxspring.link_shortener.Repository.UrlMappingRepository;
import noxxspring.link_shortener.Service.Interf.UrlMappingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UrlMappingServiceImpl implements UrlMappingService {

    private final UrlMappingRepository urlMappingRepository;

    @Override
    public UrlMappingDto createShortUrl(String originalUrl, User user) {
        String shortUrl = generateUrl();
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setUser(user);
        urlMapping.setCreatedAt(LocalDateTime.now());
        UrlMapping savedUrlMapping = urlMappingRepository.save(urlMapping);
        return convertToDto(savedUrlMapping);
    }

    private String generateUrl() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder(8);

        for(int i = 0; i < 8; i++){
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }

    private UrlMappingDto convertToDto (UrlMapping urlMapping){
        UrlMappingDto urlMappingDto = new UrlMappingDto();
        urlMappingDto.setId(urlMapping.getId());
        urlMappingDto.setOriginalUrl(urlMapping.getOriginalUrl());
        urlMappingDto.setShortUrl(urlMapping.getShortUrl());
        urlMappingDto.setClickCount(urlMapping.getClickCount());
        urlMappingDto.setCreatedAt(urlMapping.getCreatedAt());
        urlMappingDto.setUsername(urlMapping.getUser().getUsername());
        return urlMappingDto;
    }

    @Override
    public List<UrlMappingDto> getUsersUrls(User user) {
        return urlMappingRepository.findByUser(user).stream()
                .map(this::convertToDto)
                .toList();
    }
}
