package com.amsdams.ex.simplerest.rest;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FileUploadMapper {
    FileUploadDTO toFileUploadDTO(FileUpload fileUpload);

    List<FileUploadDTO> toFileUploadDTOs(List<FileUpload> fileUploads);

    FileUpload toFileUpload(FileUploadDTO fileUploadDTO);
}
