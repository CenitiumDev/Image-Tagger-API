package co.cenitiumdev.imagetagger.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImaggaResponseDto {
    private ResultDto result;
}
