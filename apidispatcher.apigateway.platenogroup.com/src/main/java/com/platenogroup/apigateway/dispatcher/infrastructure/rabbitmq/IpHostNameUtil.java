package com.platenogroup.apigateway.dispatcher.infrastructure.rabbitmq;

import java.net.InetAddress;
import java.net.UnknownHostException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author SeanYe
 */
@Slf4j
public final class IpHostNameUtil {

	public static final String getIpWithDefault(String defaultIp) {
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress().toString();
			return ip;
		} catch (UnknownHostException e) {
			log.error("Failed to get ip.", e);
			return defaultIp;
		}
	}

	public static final String getHostNameWithDefault(String defaultHostName) {
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			return addr.getHostName();
		} catch (UnknownHostException e) {
			log.error("Failed to get hostname.", e);
			return defaultHostName;
		}

	}
}
