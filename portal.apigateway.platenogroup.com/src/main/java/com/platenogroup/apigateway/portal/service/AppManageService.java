package com.platenogroup.apigateway.portal.service;

import java.util.List;

import com.platenogroup.apigateway.portal.bo.App;

public interface AppManageService {

	public void add(App app);

	public App load(String appId);

	public void remove(String appId);

	/**
	 * 列出用户拥有的app列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<App> listByUser(String userId);
}
