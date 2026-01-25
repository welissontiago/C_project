package com.example.uelissonTSS.repository;

import com.example.uelissonTSS.entities.Galery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GaleryRepository extends JpaRepository<Galery, Long> {

    Page<Galery> findAll(Pageable pageable);


}
