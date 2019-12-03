package com.platenogroup.apigateway.portal.service;

import java.util.List;

import com.platenogroup.apigateway.portal.domain.model.api.Api;

public interface ApiManageService {

	public void addApi(Api pngApi);

	public Api load(String apiId);

	public void removeApi(String apiId);

	public List<Api> list();

}
