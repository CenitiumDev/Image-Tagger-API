package co.cenitiumdev.imagetagger.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String path;
    private String tags;

    @CreationTimestamp
    private LocalDateTime uploadAt;
}
