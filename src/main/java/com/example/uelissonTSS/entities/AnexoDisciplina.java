package com.example.uelissonTSS.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Anexos_disciplina")
public class AnexoDisciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeArquivo;

    @Enumerated(EnumType.STRING)
    private TipoMaterial tipo;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] conteudo;

    private String contentType;

    public enum TipoMaterial {
        APOSTILA, MATERIAL_DIVERSO, EXERCICIO, APRESENTACAO
    }

    public AnexoDisciplina() {
    }

    public AnexoDisciplina(Long id, String nomeArquivo, TipoMaterial tipo, byte[] conteudo, String contentType) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.tipo = tipo;
        this.conteudo = conteudo;
        this.contentType = contentType;
    }

    public Long getId() {
        return id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public TipoMaterial getTipo() {
        return tipo;
    }

    public void setTipo(TipoMaterial tipo) {
        this.tipo = tipo;
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}



