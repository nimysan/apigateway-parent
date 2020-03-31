package com.platenogroup.apigateway.portal.interfaces.springsecurity;

import lombok.Data;
import lombok.ToString;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@ToString
public class JwtUserDetail implements UserDetails {
	private static final long serialVersionUID = -3375854102292237823L;
	private String username;
	private String password;
	private String userId;
	// 权限
	private Collection<? extends GrantedAuthority> authorities;

	public JwtUserDetail(String userId, String username, String password, List<GrantedAuthority> authorities) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	// 账户是否未过期
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 账户是否未锁定
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 密码是否未过期
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 账户是否激活
	@Override
	public boolean isEnabled() {
		return true;
	}

	// 获得当前登陆用户对应的对象。
	public static JwtUserDetail currentUser() {
		JwtUserDetail userDetails = (JwtUserDetail) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		System.out.println("当前用户:" + userDetails);
		return userDetails;
	}
}
