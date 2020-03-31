package com.platenogroup.apigateway.portal.infrastructure.system;

import org.springframework.security.core.context.SecurityContextHolder;

import com.platenogroup.apigateway.portal.domain.canonicalmodel.UserData;
import com.platenogroup.apigateway.portal.interfaces.springsecurity.JwtUserDetail;
import com.vluee.ddd.support.domain.AggregateId;
import com.vluee.ddd.support.domain.DomainEventPublisher;
import com.vluee.ddd.support.infrastructure.domainevent.SimpleEventPublisher;

/**
 * 系統辅助类
 * 
 * @author SeanYe
 *
 */
public final class SystemContext {

	public static final UserData getWorkuser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal != null && principal instanceof JwtUserDetail) {
			JwtUserDetail portalUserDetail = (JwtUserDetail) principal;
			return new UserData(new AggregateId(portalUserDetail.getUserId()), portalUserDetail.getUsername());
		}
		return null;
	}

	public static final DomainEventPublisher getEventPublisher() {
		return SimpleEventPublisher.INSTANCE;
	}

}
