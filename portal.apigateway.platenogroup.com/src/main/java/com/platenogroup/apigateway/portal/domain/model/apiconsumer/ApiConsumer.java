package com.platenogroup.apigateway.portal.domain.model.apiconsumer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import org.apache.commons.lang.Validate;

import com.platenogroup.apigateway.portal.domain.shared.Entity;

/**
 * <ul>
 * <li>1.
 * 每一次对下游系统的调用，都会生成x-png-customer-id/x-png-app-id,分别代表消费者和消费者app的ID，做追踪用途。</li>
 * <li>2. 一个消费者允许注册多个Application,每个application允许消费多个api.</li>
 * <li>3. 每个application+api会有独享的token访问策略</li>
 * </ul>
 * 
 * @author SeanYe
 *
 */
public class ApiConsumer implements Entity<ApiConsumer> {

	ApiConsumer() {
		// Spring data JPA needed
	}

	@Id
	private String consumerId;

	public ApiConsumer(String consumerId) {
		Validate.notNull(consumerId, "ConsumerId can't be null");
		this.consumerId = consumerId;
	}

	private List<Application> applications = new ArrayList<Application>();

	@Override
	public boolean sameIdentityAs(ApiConsumer other) {
		return this.consumerId.equals(other.getConsumerId());
	}

	public String getConsumerId() {
		return consumerId;
	}

	/**
	 * 禁用一个application.
	 * 
	 * @param applicationId
	 */
	public void deactiveApplication(Application app) {
		app.deactive();
	}

	public void addApplication(Application app) {
		if (!applications.contains(app)) {
			applications.add(app);
		}
		// need to publish event
	}
}
