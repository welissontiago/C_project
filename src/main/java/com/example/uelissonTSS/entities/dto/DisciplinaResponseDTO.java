package com.example.uelissonTSS.entities.dto;

import java.util.List;

public record DisciplinaResponseDTO(
        Long id,
        String nome,
        String descricao,
        Long imagemCapaId,
        List<String> videosYoutube,
        List<AnexoResponseDTO> anexos
) {
}
