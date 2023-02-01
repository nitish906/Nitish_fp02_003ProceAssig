package com.masai.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProceEntites {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@JsonProperty("Api")
	private String api;
	
	@JsonProperty("Link")
	private String link;
	
	@JsonProperty("Description")
	private String description;
	
	@JsonProperty("Auth")
	private String auth;
	
	
	@JsonProperty("Https")
	private boolean HTTPS;
	
	@JsonProperty("Core")
	private String core;
	
	@JsonProperty("Categories")
	private String categories;
	
	
	
	

}
