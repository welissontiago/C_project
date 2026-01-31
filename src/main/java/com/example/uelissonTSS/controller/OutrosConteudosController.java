package com.example.uelissonTSS.controller;

import com.example.uelissonTSS.entities.Galery;
import com.example.uelissonTSS.entities.OutrosConteudos;
import com.example.uelissonTSS.entities.User;
import com.example.uelissonTSS.entities.dto.DisciplinaResponseDTO;
import com.example.uelissonTSS.entities.dto.OutrosConteudosDTO;
import com.example.uelissonTSS.entities.enums.TipoContent;
import com.example.uelissonTSS.service.OutrosConteudosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("v1/api/conteudos")
public class OutrosConteudosController {

    private static final Logger logger = LoggerFactory.getLogger(GalleryController.class);

    @Autowired
    private OutrosConteudosService outrosConteudosService;

    @GetMapping
    public ResponseEntity<List<OutrosConteudos>> listAll() {
        return ResponseEntity.ok(outrosConteudosService.getAllContents());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<OutrosConteudos>> listPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(outrosConteudosService.getContentsPages(page, size));
    }

    @GetMapping("/busca")
    public ResponseEntity<OutrosConteudosDTO> getByTipo(@RequestParam String tipo) {
        TipoContent tipoEnum = TipoContent.valueOf(tipo.toUpperCase());

        return outrosConteudosService.getContentTipo(tipoEnum)
                .map(d -> ResponseEntity.ok(convertToDTO(d)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        outrosConteudosService.deleteContent(id);
        return ResponseEntity.noContent().build();
    }


    private OutrosConteudosDTO convertToDTO(OutrosConteudos entity) {
        return new OutrosConteudosDTO(
                entity.getId(),
                entity.getTema(),
                entity.getLocal(),
                entity.getData(),
                entity.getTipo());
    }

}
