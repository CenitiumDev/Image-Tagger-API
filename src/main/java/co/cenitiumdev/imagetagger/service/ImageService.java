package co.cenitiumdev.imagetagger.service;

import co.cenitiumdev.imagetagger.client.ImaggaClient;
import co.cenitiumdev.imagetagger.dto.ImaggaResponseDto;
import co.cenitiumdev.imagetagger.model.Image;
import co.cenitiumdev.imagetagger.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImaggaClient imaggaClient;
    private final CloudinaryService cloudinaryService;

    public Image uploadAndProcessImage(MultipartFile file) {

        String fileUrl = cloudinaryService.uploadFile(file);

        ImaggaResponseDto response = imaggaClient.getTags(file);
        String tags = extractTags(response);

        Image image = new Image();
        image.setFileName(file.getOriginalFilename());
        image.setPath(fileUrl);
        image.setTags(tags);

        return imageRepository.save(image);
    }

    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada con ID: " + id));
    }

    private String extractTags(ImaggaResponseDto response) {
        return response.getResult().getTags().stream()
                .filter(tag -> tag.getConfidence() > 30)
                .map(tagDto -> tagDto.getTag().get("en"))
                .limit(5)
                .collect(Collectors.joining(", "));
    }

    public List<Image> searchImagesByTag(String tag) {
        return imageRepository.findByTagsContainingIgnoreCase(tag);
    }
}
