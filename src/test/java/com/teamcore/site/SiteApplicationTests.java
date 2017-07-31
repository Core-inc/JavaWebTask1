package com.teamcore.site;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiteApplicationTests {
	private TestFactory testFactory;

	@Autowired
	public void setTestFactory(TestFactory testFactory) {
		this.testFactory = testFactory;
	}

	@Test
	public void contextLoads() {
	}

}
