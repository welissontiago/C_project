package com.example.uelissonTSS.entities;

import com.example.uelissonTSS.entities.enums.TipoContent;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "tb_contents")
public class OutrosConteudos {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tema;
    private String local;
    private String data;

    private String nomeArquivo;
    private String contentType;

    @Enumerated(EnumType.STRING)
    private TipoContent tipo;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] conteudo;


    public OutrosConteudos() {
    }

    public OutrosConteudos(String tema, String local, String data, String nomeArquivo, String contentType, TipoContent tipo, byte[] conteudo) {
        this.tema = tema;
        this.local = local;
        this.data = data;
        this.nomeArquivo = nomeArquivo;
        this.contentType = contentType;
        this.tipo = tipo;
        this.conteudo = conteudo;
    }

    public Long getId() {
        return id;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public TipoContent getTipo() {
        return tipo;
    }

    public void setTipo(TipoContent tipo) {
        this.tipo = tipo;
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OutrosConteudos that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTema(), that.getTema()) && Objects.equals(getLocal(), that.getLocal()) && Objects.equals(getData(), that.getData()) && getTipo() == that.getTipo() && Objects.deepEquals(getConteudo(), that.getConteudo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTema(), getLocal(), getData(), getTipo(), Arrays.hashCode(getConteudo()));
    }


}
