package com.platenogroup.apigateway.portal.interfaces.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.platenogroup.apigateway.portal.application.assembler.ApiAssembler;
import com.platenogroup.apigateway.portal.application.service.ApiService;
import com.platenogroup.apigateway.portal.infrastructure.exception.ExceptionHandler;
import com.platenogroup.apigateway.portal.infrastructure.util.ApplicationUtil;
import com.platenogroup.apigateway.portal.interfaces.dto.api.ApiDetailRespBody;

@RunWith(SpringRunner.class)
@WebMvcTest(ApiController.class)
@MockBean(value = { ExceptionHandler.class, ApiAssembler.class, ApplicationUtil.class, ApiService.class })
public class ApiControllerUnitTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ApiService apiService;

	@Test
	public void getTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/copy").accept(MediaType.TEXT_PLAIN)).andDo(print())
				.andExpect(status().isOk()).andExpect(new ResultMatcher() {

					@Override
					public void match(MvcResult result) throws Exception {
						assertEquals("copy", result.getResponse().getContentAsString());
					}
				});
	}

	@Test
	public void getByName() throws Exception {
		ApiDetailRespBody value = new ApiDetailRespBody();
		value.setApiCreator("test");
		when(apiService.getByName("aaaa")).thenReturn(value);
		mvc.perform(MockMvcRequestBuilders.get("/api/aaaa").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.body.apiCreator", is("test")));
	}

}