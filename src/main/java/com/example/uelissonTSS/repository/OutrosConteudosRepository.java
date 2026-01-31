package com.example.uelissonTSS.repository;

import com.example.uelissonTSS.entities.Disciplina;
import com.example.uelissonTSS.entities.OutrosConteudos;
import com.example.uelissonTSS.entities.enums.TipoContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OutrosConteudosRepository extends JpaRepository<OutrosConteudos, Long> {

    Optional<OutrosConteudos> findByTipo(TipoContent tipo);
}
