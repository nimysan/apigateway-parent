package com.platenogroup.apigateway.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;
import com.platenogroup.apigateway.portal.application.service.UserRoleManagementService;
import com.platenogroup.apigateway.portal.domain.role.PortalUserRole;
import com.platenogroup.apigateway.portal.domain.user.PortalUser;
import com.vluee.ddd.support.infrastructure.domainevent.SimpleEventPublisher;
import com.vluee.ddd.support.infrastructure.domainevent.impl.EventListenerBeanPostProcessor;

import lombok.extern.slf4j.Slf4j;

/**
 * 影响mockMvc等一些单元测试的问题解决方案：
 * https://stackoverflow.com/questions/41250177/getting-at-least-one-jpa-metamodel-must-be-present-with-webmvctest
 * 
 * @author SeanYe
 *
 */
@SpringBootApplication
@Slf4j
@Import(value = { EventListenerBeanPostProcessor.class, SimpleEventPublisher.class })
public class ApiGatewayPortalApplication implements ApplicationRunner {

	@Autowired
	UserRoleManagementService userService;

	@Autowired
	private SimpleEventPublisher simpleEventPublisher;

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayPortalApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.debug("初始化一些用户数据");
		PortalUser adminUser = userService.registerUserWithPassword("admin", "123456");
		PortalUser findByUsername = userService.findByUsername("admin");
		PortalUser apicreatorUser = userService.registerUserWithPassword("apicreator", "test111");
		PortalUser apiconsumerUser = userService.registerUserWithPassword("apiconsumer", "test222");

		PortalUserRole adminRole = userService.createRole("admin");
		PortalUserRole apiCreatorRole = userService.createRole("api_creator");
		PortalUserRole apiConsumerRole = userService.createRole("api_consumer");

		userService.assignRoleToUser(adminUser, adminRole);
		userService.assignRoleToUser(apicreatorUser, apiCreatorRole);
		userService.assignRoleToUser(apiconsumerUser, apiConsumerRole);

		log.debug("初始化完成-----------------------------------------");
		log.debug("test --- {}", findByUsername);
	}
}
