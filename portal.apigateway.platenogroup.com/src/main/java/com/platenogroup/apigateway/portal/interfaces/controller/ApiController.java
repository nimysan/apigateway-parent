package com.platenogroup.apigateway.portal.interfaces.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platenogroup.apigateway.portal.domain.service.ApiService;
import com.platenogroup.apigateway.portal.interfaces.assembler.ApiWebAssembler;
import com.platenogroup.apigateway.portal.interfaces.dto.ApiDto;
import com.platenogroup.apigateway.portal.interfaces.dto.ApiRequestDto;
import com.vluee.ddd.support.domain.AggregateId;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private ApiService apiService;

	@Autowired
	private ApiWebAssembler apiWebAssembler;

	@GetMapping
	public ResponseEntity<List<ApiDto>> list() {
		return ResponseEntity
				.ok(apiService.findAll().stream().map(t -> apiWebAssembler.assembler(t)).collect(Collectors.toList()));
	}

	/**
	 * ROLE_前缀必须带 请查看 RoleVoter
	 */
	@GetMapping("/sample")
	@PreAuthorize("hasAuthority('ROLE_api_creator')")
	public void createSampleApi() {
		apiService.createApi("business", "a.b.c", "tetst");
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_api_creator')")
	public ResponseEntity<String> create(@RequestBody ApiRequestDto apiDto) {
		AggregateId apiId = apiService.createApi(apiDto.getName(), apiDto.getAccessPath(), apiDto.getDescription());
		return ResponseEntity.ok(apiId.getId());
	}

//	@PostMapping("")
//	@PreAuthorize("hasAuthority('ROLE_api_creator')")
//	public ResponseEntity<Void> active(String id) {
//		apiService.active(new AggregateId(id));
//		return ResponseEntity.ok().build();
//	}

}
