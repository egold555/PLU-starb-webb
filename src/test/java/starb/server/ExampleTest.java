package starb.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import starb.server.controller.ExampleController;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExampleTest {

	@Autowired
	private ExampleController controller;

	@Test
	public void foo() {
		String result = controller.exampleGet();
		Assertions.assertEquals("Hello from ExampleController!", result);
	}
}