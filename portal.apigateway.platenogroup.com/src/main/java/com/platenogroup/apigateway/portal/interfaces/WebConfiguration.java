package com.platenogroup.apigateway.portal.interfaces;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vluee.codeflower.rest.GeneralRestResponseBuilder;

@Configuration
public class WebConfiguration {

	@Bean
	GeneralRestResponseBuilder createGeneralRestResponseBuilder() {
		return new GeneralRestResponseBuilder();
	}
}
