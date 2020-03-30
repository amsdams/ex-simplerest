package com.amsdams.ex.simplerest.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class FileUploadService {
    private final FileUploadRepository fileUploadRespository;

    public List<FileUpload> findAll() {
        return fileUploadRespository.findAll();
    }

    public Optional<FileUpload> findById(Long id) {
        return fileUploadRespository.findById(id);
    }

    public FileUpload save(FileUpload stock) {
        return fileUploadRespository.save(stock);
    }

    public void deleteById(Long id) {
        fileUploadRespository.deleteById(id);
    }
}
