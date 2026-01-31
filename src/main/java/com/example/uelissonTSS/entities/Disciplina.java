package com.example.uelissonTSS.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Disciplina_ID;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Galery imagemCapa;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "disciplina_id")
    private List<AnexoDisciplina> materiais;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_disciplina_videos", joinColumns = @JoinColumn(name = "disciplina_id"))
    @Column(name = "youtube_url")
    private List<String> videosYoutube;

    public List<String> getVideosYoutube() {
        return videosYoutube;
    }

    public void setVideosYoutube(List<String> videosYoutube) {
        this.videosYoutube = videosYoutube;
    }

    public List<AnexoDisciplina> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<AnexoDisciplina> materiais) {
        this.materiais = materiais;
    }

    public Galery getImagemCapa() {
        return imagemCapa;
    }

    public void setImagemCapa(Galery imagemCapa) {
        this.imagemCapa = imagemCapa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getDisciplina_ID() {
        return Disciplina_ID;
    }

}
