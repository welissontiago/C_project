package com.example.uelissonTSS.controller;

import com.example.uelissonTSS.entities.Galery;
import com.example.uelissonTSS.entities.User;
import com.example.uelissonTSS.service.GaleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(GalleryController.class);

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
                        .contentType(MediaType.parseMediaType(image.getContentType()))
                        .body(image.getData()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,
                                         @AuthenticationPrincipal User admin) {

        logger.info("Tentativa de upload recebida.");

        if (file != null) {
            logger.info("Arquivo recebido: {} | Tamanho: {} bytes | ContentType: {}",
                    file.getOriginalFilename(), file.getSize(), file.getContentType());
        } else {
            logger.warn("O arquivo (MultipartFile) está nulo!");
        }

        if (admin != null) {
            logger.info("Utilizador autenticado: {} | Authorities: {}",
                    admin.getUsername(), admin.getAuthorities());
        } else {
            logger.error("Nenhum utilizador autenticado encontrado no SecurityContext!");
        }

        try {
            galeryService.saveImage(file, admin);
            return ResponseEntity.ok("Imagem salva com sucesso no banco!");
        } catch (IOException e) {
            logger.error("Erro ao processar o arquivo: ", e);
            return ResponseEntity.internalServerError().body("Erro ao processar arquivo.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        galeryService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }

}
