package apidispatcher.apigateway.platenogroup.com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.platenogroup.apigateway.dispatcher.ApiGatewaEngineApplication;
import com.platenogroup.apigateway.dispatcher.domain.filter.FilterConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiGatewaEngineApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LocalRouterTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void routeToLocalRequest() {
		// proxyLocal将请求代理的本地local
		webTestClient.get().uri("/api/sample").exchange().expectStatus().isOk();
	}

	@Test
	public void postHeaderFilter() {
		// proxyLocal将请求代理的本地local
		webTestClient.get().uri("/api/sample").exchange().expectStatus().isOk().expectHeader()
				.valueEquals("x-png-gateway", "platenogroup");
	}

	@Test
	public void requestId() {
		// proxyLocal将请求代理的本地local
		webTestClient.get().uri("/api/sample").exchange().expectStatus().isOk().expectHeader()
				.valueMatches(FilterConfiguration.X_PNG_REQUEST_ID, "test.*");
	}

	@Test
	public void postHeaderVersionFilter() {
		// proxyLocal将请求代理的本地local
		webTestClient.get().uri("/api/sample").exchange().expectStatus().isOk().expectHeader()
				.valueEquals("x-png-gateway-version", "1.0");
	}

}
