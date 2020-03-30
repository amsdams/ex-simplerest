package com.amsdams.ex.simplerest.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/fileUploads")
public class FileUploadController {
    private final FileUploadService fileUploadService;
    private final FileUploadMapper fileUploadMapper;

    @GetMapping
    public ResponseEntity<List<FileUploadDTO>> findAll() {
        return ResponseEntity.ok(fileUploadMapper.toFileUploadDTOs(fileUploadService.findAll()));
    }

    @PostMapping
    public ResponseEntity<FileUploadDTO> create(@RequestBody FileUploadDTO fileUploadDTO) {
        fileUploadService.save(fileUploadMapper.toFileUpload(fileUploadDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(fileUploadDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileUploadDTO> findById(@PathVariable Long id) {
        Optional<FileUpload> fileUpload = fileUploadService.findById(id);

        return ResponseEntity.ok(fileUploadMapper.toFileUploadDTO(fileUpload.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FileUploadDTO> update(@PathVariable Long id, @RequestBody FileUploadDTO fileUploadDTO) {
        FileUpload fileUpload = fileUploadMapper.toFileUpload(fileUploadDTO);
        fileUpload.setId(id);

        fileUploadService.save(fileUpload);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(fileUploadDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        fileUploadService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
