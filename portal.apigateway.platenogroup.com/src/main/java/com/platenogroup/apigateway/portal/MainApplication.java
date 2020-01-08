package com.platenogroup.apigateway.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.platenogroup.apigateway.portal.interfaces.controller.base.BaseController;

/**
 * 影响mockMvc等一些单元测试的问题解决方案：
 * https://stackoverflow.com/questions/41250177/getting-at-least-one-jpa-metamodel-must-be-present-with-webmvctest
 * 
 * @author SeanYe
 *
 */
@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}
