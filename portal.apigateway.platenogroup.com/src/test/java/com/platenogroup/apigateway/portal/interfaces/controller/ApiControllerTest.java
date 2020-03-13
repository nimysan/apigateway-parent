package com.platenogroup.apigateway.portal.interfaces.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.platenogroup.apigateway.portal.domain.service.UserService;

@WebMvcTest(ApiController.class)
class ApiControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService service;

	@Test
	public void registerUser() throws Exception {
		when(service.createUser("123", "23333"));
		this.mockMvc.perform(post("/api")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, Mock")));
	}

}
