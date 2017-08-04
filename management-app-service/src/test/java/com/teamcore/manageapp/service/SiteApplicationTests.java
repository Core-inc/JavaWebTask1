package com.teamcore.manageapp.service;

import com.teamcore.manageapp.service.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
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
