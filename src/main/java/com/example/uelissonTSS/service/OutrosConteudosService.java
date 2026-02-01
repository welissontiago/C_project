package com.example.uelissonTSS.service;

import com.example.uelissonTSS.entities.Galery;
import com.example.uelissonTSS.entities.OutrosConteudos;
import com.example.uelissonTSS.entities.User;
import com.example.uelissonTSS.entities.dto.OutrosConteudosDTO;
import com.example.uelissonTSS.entities.enums.TipoContent;
import com.example.uelissonTSS.repository.OutrosConteudosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class OutrosConteudosService {

    @Autowired
    private OutrosConteudosRepository outrosConteudosRepository;

    @Transactional
    public OutrosConteudos create(OutrosConteudosDTO dto, MultipartFile file) throws IOException {
        OutrosConteudos outrosConteudos = new OutrosConteudos();
        outrosConteudos.setTema(dto.tema());
        outrosConteudos.setData(dto.data());
        outrosConteudos.setLocal(dto.local());
        outrosConteudos.setTipo(dto.tipo());


        outrosConteudos.setConteudo(file.getBytes());
        outrosConteudos.setNomeArquivo(file.getOriginalFilename());
        outrosConteudos.setContentType(file.getContentType());

        return outrosConteudosRepository.save(outrosConteudos);
    }


    public Page<OutrosConteudos> getContentsPages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("creationTimestamp").descending());
        return outrosConteudosRepository.findAll(pageable);
    }

    public Optional<OutrosConteudos> getContentById(Long id) {
        return outrosConteudosRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<OutrosConteudos> getContentTipo(TipoContent tipo) {
        return outrosConteudosRepository.findByTipo(tipo);
    }

    public List<OutrosConteudos> getAllContents() {
        return outrosConteudosRepository.findAll();
    }

    public void deleteContent(Long id) {
        outrosConteudosRepository.deleteById(id);
    }


}
