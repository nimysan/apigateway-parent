package com.platenogroup.apigateway.portal.infrastructure.system;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.platenogroup.apigateway.portal.interfaces.springsecurity.MD5Util;

@Component
public class ApiPortalPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return MD5Util.string2MD5(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encode(rawPassword).contentEquals(encodedPassword);
	}

}
