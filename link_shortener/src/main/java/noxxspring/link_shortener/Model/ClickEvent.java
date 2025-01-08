package noxxspring.link_shortener.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "clickEvent")
public class ClickEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "urlMapping_id")
    private UrlMapping urlMapping;
}
