package com.example.uelissonTSS.entities.dto;

import com.example.uelissonTSS.entities.enums.TipoContent;

public record OutrosConteudosDTO(
        Long id,
        String tema,
        String local,
        String data,
        TipoContent tipo
) {
}
