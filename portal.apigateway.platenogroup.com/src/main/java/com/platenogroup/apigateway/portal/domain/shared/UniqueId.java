package com.platenogroup.apigateway.portal.domain.shared;

import java.util.UUID;

public class UniqueId {

	public static Long nextLongId() {
		return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
	}
}
