package com.platenogroup.apigateway.portal.infrastructure.system;

import org.springframework.security.core.context.SecurityContextHolder;

import com.platenogroup.apigateway.portal.domain.auth.PortalUserDetail;
import com.platenogroup.apigateway.portal.domain.canonicalmodel.UserData;

/**
 * 系統辅助类
 * 
 * @author SeanYe
 *
 */
public final class SystemContext {

	public static final UserData getWorkuser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal != null && principal instanceof PortalUserDetail) {
			PortalUserDetail portalUserDetail = (PortalUserDetail) principal;
			return new UserData(portalUserDetail.getAggregateId(), portalUserDetail.getUsername());
		}
		return null;
	}
}
