package com.example.uelissonTSS.service;

import com.example.uelissonTSS.entities.Galery;
import com.example.uelissonTSS.entities.User;
import com.example.uelissonTSS.repository.GaleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class GaleryService {

    @Autowired
    private GaleryRepository galeryRepository;

    public Galery saveImage(MultipartFile file, User admin) throws IOException {
        Galery image = new Galery();
        image.setData(file.getBytes());
        image.setContentType(file.getContentType());
        image.setUser(admin);

        return galeryRepository.save(image);
    }

    public Page<Galery> getImagesPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("creationTimestamp").descending());
        return galeryRepository.findAll(pageable);
    }

    public Optional<Galery> getImageById(Long id) {
        return galeryRepository.findById(id);
    }

    public List<Galery> getAllImages() {
        return galeryRepository.findAll();
    }

    public void deleteImage(Long id) {
        galeryRepository.deleteById(id);
    }
}
