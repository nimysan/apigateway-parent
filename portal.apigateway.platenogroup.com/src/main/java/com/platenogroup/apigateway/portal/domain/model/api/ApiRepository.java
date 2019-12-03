package com.platenogroup.apigateway.portal.domain.model.api;

import java.util.List;

/**
 * 1. 为什么Repository接口放在model里面，而不是infrastructure里面， 依据 依赖倒置原则。
 * 2. 复杂的查询通过Specification模式实现。
 * 
 * @author SeanYe
 *
 */
public interface ApiRepository {

	/**
	 * 
	 * @return
	 */
	List<Api> findAll();

	/**
	 * 没有add/update区分， 只有sotre/save概念。 业务意义就是存储
	 * 
	 * @param api
	 */
	void store(Api api);

	/**
	 * 
	 * @param apiId
	 * @return
	 */
	Api find(ApiId apiId);

}
