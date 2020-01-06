package com.platenogroup.apigateway.portal.application.service;

import com.platenogroup.apigateway.portal.domain.model.api.Api;
import com.platenogroup.apigateway.portal.infrastructure.exception.BusinessException;

/**
 * 1. This is Application Service。 输入输出可以是interface层的类和对象， 但是不能依賴interfaces層的任何定義。
 * 2. 特别复杂的Domain逻辑请写入domain service里面去。
 * 
 * @author SeanYe
 *
 */
public interface ApiService {

	public Api getByName(String apiName) throws BusinessException;

	public String addApi(Api api) throws BusinessException;

	public void deleteApi(String apiId) throws BusinessException;

	public void deactive(String apiName) throws BusinessException;
}
