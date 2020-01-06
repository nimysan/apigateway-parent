package com.platenogroup.apigateway.portal.domain.model.api;

import com.platenogroup.apigateway.portal.domain.shared.ValueObject;

public class ApiTag implements ValueObject<ApiTag> {

	private static final long serialVersionUID = -3861485645567208774L;

	private String tagValue;
	private String createBy;

	public ApiTag(String tagContent) {
		this.tagValue = tagContent;
	}

	@Override
	public boolean sameValueAs(ApiTag other) {
		return this.tagValue.contentEquals(other.tagValue);
	}

	public String getTagValue() {
		return tagValue;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tagValue == null) ? 0 : tagValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApiTag other = (ApiTag) obj;
		if (tagValue == null) {
			if (other.tagValue != null)
				return false;
		} else if (!tagValue.equals(other.tagValue))
			return false;
		return true;
	}

}
