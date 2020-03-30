package com.amsdams.ex.simplerest.rest;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class FileUploadDTO {
	private String name;
	private String description;
	private Long size;
	/* hide from swagger */
	/* @ApiModelProperty(hidden = true) */
	// @JsonIgnoreProperties({ "readme" })

	// use json ignore
	@JsonIgnore
	private String readmeFirst;
	
	
	@Setter(value = AccessLevel.NONE)
	@Getter(value = AccessLevel.NONE)
	private String readmeSecond;
	
	@Setter(AccessLevel.NONE)
	private String readmeThird;
	
	@Setter(AccessLevel.NONE)
	private Date updatedAt;
	
	@Setter(AccessLevel.NONE)
	private Date createdAt;
	
	

}
