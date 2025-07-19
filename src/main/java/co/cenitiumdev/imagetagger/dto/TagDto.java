package co.cenitiumdev.imagetagger.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagDto {
    private double confidence;
    private Map<String, String> tag;
}
