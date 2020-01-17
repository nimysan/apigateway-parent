package com.platenogroup.apigateway.portal.application.service;

import java.util.Collection;
import java.util.Optional;

import com.platenogroup.apigateway.portal.domain.model.api.Api;

/**
 * 1. This is Application Service。 输入输出可以是interface层的类和对象，
 * 但是不能依賴interfaces層的任何定義。 2. 特别复杂的Domain逻辑请写入domain service里面去。
 * 
 * @author SeanYe
 *
 */
public interface ApiService {

	public Optional<Api> getByName(String apiName);

	public String addApi(Api api);

	public Collection<Api> listAll();

	public void deleteApi(String apiId);

	public void deactive(String apiName);

}
