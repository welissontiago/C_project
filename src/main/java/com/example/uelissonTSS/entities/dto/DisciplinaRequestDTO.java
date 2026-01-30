package com.example.uelissonTSS.entities.dto;

import java.util.List;

public record DisciplinaRequestDTO(String nome,
                                   String descricao,
                                   List<String> videosYoutube,
                                   Long imagemCapaId,
                                   List<AnexoRequestDTO> materiais) {
}
