package com.amsdams.ex.simplerest.rest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.amsdams.ex.simplerest.service.FileLoaderImpl;

@Mapper(componentModel = "spring", uses = { FileLoaderImpl.class })
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
	
    
    
	@Mapping(source = "data", target = "data", qualifiedByName = "loadFile") 
	@Override
	ProductDTO toDto(Product fileUpload);

	@Mapping(source = "data", target = "data", qualifiedByName = "createFile") 
	@Override
    Product toEntity(ProductDTO fileUploadDTO);
    
	default Product fromId(Long id) {
		if (id == null) {
			return null;
		}
		Product product = new Product();
		product.setId(id);
		return product;
	}
	
	

}
