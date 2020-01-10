package com.platenogroup.apigateway.common.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 
 * @author SeanYe
 *
 */
public final class IpHostNameUtil {

	public static final String getIpWithDefault(String defaultIp) {
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress().toString();
			return ip;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return defaultIp;
		}
	}

	public static final String getHostNameWithDefault(String defaultHostName) {
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			return addr.getHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return defaultHostName;
		}

	}
}
