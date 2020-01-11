package apidispatcher.apigateway.platenogroup.com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.platenogroup.apigateway.dispatcher.ApiGatewaEngineApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiGatewaEngineApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleLocalControler {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void testSample() throws Exception {
		webTestClient.get().uri("/sample").exchange().expectStatus().isOk();
	}

	@Test
	public void testSamplePathVariable() throws Exception {
		webTestClient.get().uri("/sample/test").exchange().expectStatus().isOk().expectBody().equals("test");
	}

	@Test
	public void test404Error() throws Exception {
		webTestClient.get().uri("/404").exchange().expectStatus().isNotFound();
	}

}
