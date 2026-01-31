package com.example.uelissonTSS.service;

import com.example.uelissonTSS.entities.AnexoDisciplina;
import com.example.uelissonTSS.entities.Disciplina;
import com.example.uelissonTSS.entities.dto.DisciplinaRequestDTO;
import com.example.uelissonTSS.repository.DisciplinaRepository;
import com.example.uelissonTSS.repository.GaleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private GaleryRepository galeryRepository;

    @Transactional(readOnly = true)
    public List<Disciplina> findAll() {
        return disciplinaRepository.findAllWithMateriais();
    }

    @Transactional(readOnly = true)
    public Optional<Disciplina> findById(Long id) {
        return disciplinaRepository.findByIdWithMateriais(id);
    }

    @Transactional(readOnly = true)
    public Optional<Disciplina> findByNome(String nome) {
        return disciplinaRepository.findAll().stream()
                .filter(d -> d.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    @Transactional
    public Disciplina create(DisciplinaRequestDTO dto) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.nome());
        disciplina.setDescricao(dto.descricao());
        disciplina.setVideosYoutube(dto.videosYoutube());


        if (dto.imagemCapaId() != null) {
            galeryRepository.findById(dto.imagemCapaId())
                    .ifPresent(disciplina::setImagemCapa);
        }

        if (dto.materiais() != null) {
            List<AnexoDisciplina> anexos = dto.materiais().stream().map(m -> {
                AnexoDisciplina anexo = new AnexoDisciplina();
                anexo.setNomeArquivo(m.nomeArquivo());
                anexo.setTipo(AnexoDisciplina.TipoMaterial.valueOf(m.tipo()));
                anexo.setContentType(m.contentType());
                anexo.setConteudo(java.util.Base64.getDecoder().decode(m.conteudoBase64()));
                return anexo;
            }).collect(Collectors.toList());

            disciplina.setMateriais(anexos);
        }

        return disciplinaRepository.save(disciplina);
    }

    @Transactional
    public Optional<Disciplina> update(Long id, DisciplinaRequestDTO dto) {
        return disciplinaRepository.findById(id).map(disciplina -> {
            updateEntityFromDto(disciplina, dto);
            return disciplinaRepository.save(disciplina);
        });
    }

    @Transactional
    public void delete(Long id) {
        disciplinaRepository.deleteById(id);
    }

    private void updateEntityFromDto(Disciplina disciplina, DisciplinaRequestDTO dto) {
        disciplina.setNome(dto.nome());
        disciplina.setDescricao(dto.descricao());
        disciplina.setVideosYoutube(dto.videosYoutube());
    }
}
