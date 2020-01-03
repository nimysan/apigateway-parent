package com.platenogroup.apigateway.portal.application.service;

import com.platenogroup.apigateway.common.interfaces.dto.base.IdRespBody;
import com.platenogroup.apigateway.common.interfaces.dto.base.RequestDto;
import com.platenogroup.apigateway.portal.infrastructure.exception.BusinessException;
import com.platenogroup.apigateway.portal.interfaces.dto.api.ApiDetailRespBody;
import com.platenogroup.apigateway.portal.interfaces.dto.api.ApiDto;
import com.platenogroup.apigateway.portal.interfaces.dto.api.DeleteApiRespBody;

/**
 * 所有的方法输入都必须是DTO，输出可以是模型或者基础类。
 * 
 * @author SeanYe
 *
 */
public interface ApiService {

	public ApiDetailRespBody getByName(String apiName);

	public IdRespBody addApi(RequestDto<ApiDto> apiDto) throws BusinessException;

	/**
	 * 通过id删除API
	 * 
	 * @param apiId
	 */
	public DeleteApiRespBody deleteApi(String apiId);

	public void deactive(String apiName) throws BusinessException;
}
