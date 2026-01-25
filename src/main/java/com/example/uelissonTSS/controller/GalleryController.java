package com.example.uelissonTSS.controller;

import com.example.uelissonTSS.entities.Galery;
import com.example.uelissonTSS.entities.User;
import com.example.uelissonTSS.service.GaleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("v1/api/galeria")
public class GalleryController {

    @Autowired
    private GaleryService galeryService;

    @GetMapping
    public ResponseEntity<List<Galery>> listAll() {
        return ResponseEntity.ok(galeryService.getAllImages());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Galery>> listPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(galeryService.getImagesPaginated(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        return galeryService.getImageById(id)
                .map(image -> ResponseEntity.ok()
                        .body(image.getData()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,
                                         @AuthenticationPrincipal User admin) {
        try {
            galeryService.saveImage(file, admin);
            return ResponseEntity.ok("Imagem salva com sucesso no banco!");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Erro ao processar arquivo.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        galeryService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }

}
