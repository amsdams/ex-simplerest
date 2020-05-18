package com.amsdams.ex.simplerest.rest;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ProductDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String title;
	private BigDecimal price;
	private String description;
	private String imageUrl;
	private String teaserUrl;

	@JsonIgnore
	private Instant updatedAt;

	@JsonIgnore
	private Instant createdAt;

	private LocalDate publishOn;
    private LocalDate unpublishOn;
    private LocalDate archiveOn;
    
    private Status status;
}
