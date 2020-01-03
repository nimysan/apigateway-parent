package com.platenogroup.apigateway.common.interfaces.dto.base;

/**
 * 通常我们需要增加一个模型，经常返回一个唯一ID作为下一个操作的起点。 
 * 
 * @author SeanYe
 *
 */
public class IdRespBody extends ResponseBody {

	private String id;

	public IdRespBody(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
