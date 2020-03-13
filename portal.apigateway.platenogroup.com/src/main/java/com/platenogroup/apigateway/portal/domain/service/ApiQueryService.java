package com.platenogroup.apigateway.portal.domain.service;

import com.platenogroup.apigateway.portal.interfaces.webvo.ApiWebVo;
import com.platenogroup.apigateway.portal.interfaces.webvo.PaginatedResult;

public interface ApiQueryService {

	PaginatedResult<ApiWebVo> queryApiByCreator(String userId, int pageNum, int pageSize);

}
