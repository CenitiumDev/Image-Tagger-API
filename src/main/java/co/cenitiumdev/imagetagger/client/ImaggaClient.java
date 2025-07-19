package co.cenitiumdev.imagetagger.client;

import co.cenitiumdev.imagetagger.dto.ImaggaResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Component
public class ImaggaClient {

    private final String imaggaApiUrl = "https://api.imagga.com/v2/tags";
    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiKey;
    private final String apiSecret;

    public ImaggaClient(@Value("${imagga.api.key}") String apiKey,
                        @Value("${imagga.api.secret}") String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    public ImaggaResponseDto getTags(MultipartFile file) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", file.getResource());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.add("Authorization", getAuthorizationHeader());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(imaggaApiUrl, requestEntity, ImaggaResponseDto.class);
    }

    private String getAuthorizationHeader() {
        String credentials = apiKey + ":" + apiSecret;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }
}