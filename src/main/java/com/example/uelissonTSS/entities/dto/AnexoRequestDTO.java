package com.example.uelissonTSS.entities.dto;

public record AnexoRequestDTO(String nomeArquivo,
                              String tipo,
                              String conteudoBase64,
                              String contentType) {
}
