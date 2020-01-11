package com.platenogroup.apigateway.common.interfaces.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.platenogroup.apigateway.common.constants.ReturnCode;

@WebMvcTest(BaseRestController.class)
@RunWith(JUnitPlatform.class)
class BaseControllerTest {

	@Autowired
	private MockMvc mvc;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void verifyNormalResponse() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/sample/normal")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value(ReturnCode.SUCCESS)).andExpect(jsonPath("$.body.a").value("av"));
	}

	@Test
	void verify404() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/sample/notexist")).andDo(print())
				.andExpect(status().is4xxClientError());
	}

	@Test
	void verifyArgumentMissing() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/sample/argmissing")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value(ReturnCode.UNKNOWN_ERROR));
//				.andExpect(jsonPath("$.message").value("Required String parameter 'a1' is not present"));
	}

	@Test
	void verifyRuntimeException() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/sample/exception").accept(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(status().isOk());
	}

}
