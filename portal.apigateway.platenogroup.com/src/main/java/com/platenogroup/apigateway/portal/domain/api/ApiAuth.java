package com.platenogroup.apigateway.portal.domain.api;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ApiAuth {

	@Id
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;
}
