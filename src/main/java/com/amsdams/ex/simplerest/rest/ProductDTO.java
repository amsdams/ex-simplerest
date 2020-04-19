package com.amsdams.ex.simplerest.rest;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ProductDTO {

	private Long id;
	private String title;
	private BigDecimal price;
	private String description;
	private String imageUrl;
	private String teaserUrl;

	@JsonIgnore
	private Date updatedAt;

	@JsonIgnore
	private Date createdAt;

}
