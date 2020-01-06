package com.platenogroup.apigateway.portal.domain.model.api;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.platenogroup.apigateway.portal.constants.CommonConstants;
import com.platenogroup.apigateway.portal.domain.shared.Entity;

@javax.persistence.Entity
public class SimpleTag implements Entity<SimpleTag> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = CommonConstants.DIST_ID_GENERATOR_NAME)
	@GenericGenerator(name = CommonConstants.DIST_ID_GENERATOR_NAME, strategy = CommonConstants.DIST_ID_GENERATOR_IMPL)
	private String id;

	private String tagValue;
	private String createBy;

	public SimpleTag(String tagContent) {
		this.tagValue = tagContent;
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

	public String getId() {
		return id;
	}

	public SimpleTag() {
		// spring-data-jpa needed
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
		SimpleTag other = (SimpleTag) obj;
		if (tagValue == null) {
			if (other.tagValue != null)
				return false;
		} else if (!tagValue.equals(other.tagValue))
			return false;
		return true;
	}

	@Override
	public boolean sameIdentityAs(SimpleTag other) {
		return this.getId().contentEquals(other.getId());
	}

}
