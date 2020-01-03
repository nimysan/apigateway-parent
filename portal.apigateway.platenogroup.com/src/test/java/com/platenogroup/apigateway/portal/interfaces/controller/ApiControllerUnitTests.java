package com.platenogroup.apigateway.portal.interfaces.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.alibaba.fastjson.JSON;
import com.platenogroup.apigateway.common.interfaces.dto.base.IdRespBody;
import com.platenogroup.apigateway.common.interfaces.dto.base.RequestDto;
import com.platenogroup.apigateway.portal.application.assembler.ApiAssembler;
import com.platenogroup.apigateway.portal.application.service.ApiService;
import com.platenogroup.apigateway.portal.infrastructure.exception.BusinessException;
import com.platenogroup.apigateway.portal.infrastructure.exception.ExceptionHandler;
import com.platenogroup.apigateway.portal.infrastructure.util.ApplicationUtil;
import com.platenogroup.apigateway.portal.interfaces.dto.api.ApiDetailRespBody;
import com.platenogroup.apigateway.portal.interfaces.dto.api.ApiDto;

@RunWith(SpringRunner.class)
@WebMvcTest(ApiController.class)
@MockBean(value = { ExceptionHandler.class, ApiAssembler.class, ApplicationUtil.class, ApiService.class })
public class ApiControllerUnitTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ApiService apiService;

	@Autowired
	private ApiController controller;

	/**
	 * 为了测试，真的是什么都能干的出来
	 */
	@Before
	public void setUp() {
		ApplicationUtil au = new ApplicationUtil();
		au.setApplicationContext(mock(ApplicationContext.class));
		controller.setApplicationUtil(au);
		ExceptionHandler exceptionHandler = new ExceptionHandler();
		exceptionHandler.setApplicationUtil(au);
		controller.setExceptionHandler(exceptionHandler);
	}

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
	public void testDeactive() throws Exception {
		mvc.perform(MockMvcRequestBuilders.put("/api/deactive/test")).andDo(print()).andExpect(status().isOk());
		verify(apiService).deactive("test");
	}

	@Test
	public void testDeactiveException() throws Exception {
		BusinessException businessException = new BusinessException("9000", "Api Does not exist");
		doThrow(businessException).when(apiService).deactive("test");
		mvc.perform(MockMvcRequestBuilders.put("/api/deactive/test")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.returnCode", is("9000")));
		verify(apiService).deactive("test");
	}

	@Test
	public void testCreateApi() throws Exception {
		RequestDto<ApiDto> createApiDao = createApiDao("botao");
		when(apiService.addApi(Mockito.any(RequestDto.class))).thenReturn(new IdRespBody("1234"));
		String jsonString = JSON.toJSONString(createApiDao);
		mvc.perform(
				MockMvcRequestBuilders.post("/api/").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonString))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.body.id", is("1234")));
	}

	@Test
	public void testCreateApiException() throws Exception {
		RequestDto<ApiDto> apiCreateParam = createApiDao("botao");
		BusinessException businessException = new BusinessException("9000", "Api Does not exist");
		doThrow(businessException).when(apiService).addApi(Mockito.any(RequestDto.class));
		String jsonString = JSON.toJSONString(apiCreateParam);
		mvc.perform(
				MockMvcRequestBuilders.post("/api/").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonString))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.returnCode", is("9000")));
	}

	private RequestDto<ApiDto> createApiDao(String apiName) {
		RequestDto<ApiDto> rd = new RequestDto<ApiDto>();
		rd.setRequestId("123");
		rd.setChannel("test");
		ApiDto body = new ApiDto();
		body.setName(apiName);
		rd.setBody(body);
		return rd;
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