package com.platenogroup.apigateway.portal.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platenogroup.apigateway.portal.domain.model.api.Api;
import com.platenogroup.apigateway.portal.domain.model.api.ApiRepository;
import com.platenogroup.apigateway.portal.infrastructure.exception.BusinessException;

/**
 * 服务层只能依赖于下面的Domain和Repository层。
 * 
 * @author SeanYe
 *
 */
@Service
public class ApiServiceImpl implements ApiService {

	@Autowired
	private ApiRepository apiRepository;

	@Override
	public Api getByName(String apiName) throws BusinessException {
		return apiRepository.findByName(apiName).get();
	}

	@Override
	public String addApi(Api api) throws BusinessException {
		//按照更严格的说法，这个逻辑应该写入到domain service层去。 綜合考量代碼複雜度和架構严谨性， 放到此处。
		if (this.getByName(api.getName()) != null) {
			throw new BusinessException("重複API");
		}
		Api save = apiRepository.save(api);
		return save.getId();
	}

	@Override
	public void deleteApi(String apiId) throws BusinessException {
		apiRepository.deleteById(apiId);
	}

	@Override
	public void deactive(String apiName) throws BusinessException {
		Api byName = this.getByName(apiName);
		if (byName != null) {
			byName.deactive();
			apiRepository.save(byName);
		}
	}

}
