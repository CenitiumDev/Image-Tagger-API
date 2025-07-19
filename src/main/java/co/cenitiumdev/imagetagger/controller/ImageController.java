package co.cenitiumdev.imagetagger.controller;

import co.cenitiumdev.imagetagger.model.Image;
import co.cenitiumdev.imagetagger.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
@Tag(name = "Image Controller", description = "API para subir, buscar y obtener imágenes.")
public class ImageController {

    private final ImageService imageService;

    @Operation(summary = "Sube una imagen y la analiza para obtener etiquetas.")
    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(
            @Parameter(description = "El archivo de imagen a subir.")
            @RequestParam("file") MultipartFile file) {
        Image image = imageService.uploadAndProcessImage(file);
        return ResponseEntity.status(201).body(image);
    }

    @Operation(
            summary = "Obtiene una imagen por su ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Imagen encontrada.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Image.class))),
                    @ApiResponse(responseCode = "404", description = "Imagen no encontrada para el ID proporcionado.")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Image> getImage(
            @Parameter(description = "ID de la imagen a obtener.", required = true, example = "1")
            @PathVariable Long id) {

        Image image = imageService.getImageById(id);
        return ResponseEntity.ok(image);
    }

    @Operation(
            summary = "Busca imágenes por una etiqueta",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Búsqueda completada. Devuelve una lista de imágenes (puede estar vacía).")
            }
    )
    @GetMapping("/search")
    public ResponseEntity<List<Image>> searchImages(
            @Parameter(description = "Etiqueta a buscar en las imágenes.", required = true, example = "cat")
            @RequestParam String tag) {

        List<Image> images = imageService.searchImagesByTag(tag);
        return ResponseEntity.ok(images);
    }
}
