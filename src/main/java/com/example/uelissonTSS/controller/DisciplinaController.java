package com.example.uelissonTSS.controller;

import com.example.uelissonTSS.entities.Disciplina;
import com.example.uelissonTSS.entities.dto.AnexoResponseDTO;
import com.example.uelissonTSS.entities.dto.DisciplinaRequestDTO;
import com.example.uelissonTSS.entities.dto.DisciplinaResponseDTO;
import com.example.uelissonTSS.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<List<DisciplinaResponseDTO>> getAll() {
        List<DisciplinaResponseDTO> list = disciplinaService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> getById(@PathVariable Long id) {
        return disciplinaService.findById(id)
                .map(d -> ResponseEntity.ok(convertToDTO(d)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/busca")
    public ResponseEntity<DisciplinaResponseDTO> getByNome(@RequestParam String nome) {
        return disciplinaService.findByNome(nome)
                .map(d -> ResponseEntity.ok(convertToDTO(d)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DisciplinaResponseDTO> create(@RequestBody DisciplinaRequestDTO dto) {
        Disciplina nova = disciplinaService.create(dto);
        return ResponseEntity.ok(convertToDTO(nova));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> update(@PathVariable Long id, @RequestBody DisciplinaRequestDTO dto) {
        return disciplinaService.update(id, dto)
                .map(d -> ResponseEntity.ok(convertToDTO(d)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        disciplinaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private DisciplinaResponseDTO convertToDTO(Disciplina d) {
        return new DisciplinaResponseDTO(
                d.getDisciplina_ID(),
                d.getNome(),
                d.getDescricao(),
                d.getImagemCapa() != null ? d.getImagemCapa().getImageId() : null,
                d.getVideosYoutube(),
                d.getMateriais().stream()
                        .map(a -> new AnexoResponseDTO(a.getId(), a.getNomeArquivo(), a.getTipo().name()))
                        .collect(Collectors.toList())
        );
    }
}
