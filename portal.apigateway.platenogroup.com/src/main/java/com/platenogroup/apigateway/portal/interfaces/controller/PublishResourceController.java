package com.platenogroup.apigateway.portal.interfaces.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.platenogroup.apigateway.portal.domain.service.ApiService;

/**
 * /publish?type=api&resourceid=xxx-xxx-xxx-xxx
 * 
 * @author SeanYe
 *
 */
@RestController
@RequestMapping("/publish")
public class PublishResourceController {

	/**
	 * 当前只支持一种资源
	 * 
	 * @author SeanYe
	 *
	 */
	private enum Resources {
		api
	}

	@Autowired
	public ApiService apiDomainService;

	@PostMapping
	public void publishResource(@RequestParam String resource, @RequestParam String resourceId) {
		switch (Resources.valueOf(resource)) {
		case api:
			apiDomainService.publish(resourceId);
			break;
		default:
			throw new ResourceAccessException(String.format("资源类型不支持操纵，当前支持的类型有  %s", Resources.values().toString()));
		}
	}
}
