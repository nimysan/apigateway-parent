package apidispatcher.apigateway.platenogroup.com;

import org.junit.Test;

import com.google.common.base.Objects;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

	ComplexObject a = new ComplexObject();

	Integer p = 5;

	@Test
	public void verifyDeferForComplexObjectCreateTime() {
		Mono<ComplexObject> monoJust = Mono.just(new ComplexObject("mono"));
		Mono<ComplexObject> monoDefer = Mono.defer(() -> Mono.just(new ComplexObject("defer")));
		//在未被订阅之前， new ComplexObject("defer") 这句代码并没有被真正执行。 Mono的创建延后了。 Defer的意義。
		System.out.println("Before subscribe");
		monoJust.subscribe(MonoFluxTest::print);
		monoDefer.subscribe(MonoFluxTest::print);
	}

	/**
	 * 尝试理解defer吧。 虽然好难理解
	 * https://stackoverflow.com/questions/55955567/what-does-mono-defer-do
	 */
	@Test
	public void verifyDeferForComplexObject() {
		a.setValue(5);
		Mono<ComplexObject> monoJust = Mono.just(a);
		Mono<ComplexObject> monoDefer = Mono.defer(() -> Mono.just(a));

		monoJust.subscribe(integer1 -> System.out.println(integer1));
		monoDefer.subscribe(integer1 -> System.out.println(integer1));

		a.setValue(8);
		monoJust.subscribe(integer1 -> System.out.println(integer1));// 5
		monoDefer.subscribe(integer1 -> System.out.println(integer1));// 8
	}

	@Test
	public void verifyDeferForSimpleObject() {
		Mono<Integer> monoJust = Mono.just(getP());
		Mono<Integer> monoDefer = Mono.defer(() -> Mono.just(getP()));

		monoJust.subscribe(MonoFluxTest::print);
		monoDefer.subscribe(MonoFluxTest::print);
		assignValue();
		monoJust.subscribe(MonoFluxTest::print);// 5
		monoDefer.subscribe(MonoFluxTest::print);// 8

		System.out.println("tttt");
	}

	public Integer getP() {
		return p;
	}

	private void assignValue() {
		System.out.println("Please debug here");
		p = 7;
	}
	

	public static final void print(Object t) {
		if (t instanceof Integer) {
			Integer pp = (Integer) t;
			if (pp == 7) {
				System.out.println("222");
			}
		}
		System.out.println(t + "-" + Addresser.addressOf(t));
	}

}
