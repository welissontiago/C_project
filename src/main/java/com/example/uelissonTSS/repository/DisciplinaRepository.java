package com.example.uelissonTSS.repository;

import com.example.uelissonTSS.entities.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    Optional<Disciplina> findByNome(String nome);

    @Query("SELECT d FROM Disciplina d LEFT JOIN FETCH d.materiais")
    List<Disciplina> findAllWithMateriais();

    @Query("SELECT d FROM Disciplina d LEFT JOIN FETCH d.materiais WHERE d.Disciplina_ID = :id")
    Optional<Disciplina> findByIdWithMateriais(Long id);
}
