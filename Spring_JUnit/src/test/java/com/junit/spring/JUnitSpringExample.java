package com.junit.spring;

import com.junit.spring.main.AppConfig;
import com.junit.spring.service.SampleService;
import com.unit.obj.Order;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= AppConfig.class,loader=AnnotationConfigContextLoader.class)
public class JUnitSpringExample {
	
	@Autowired
	private SampleService sampleService;

	
	@BeforeClass
	public static void setUp() {
		System.out.println("-----> BEFORE CLASS <-----");
	}
	
	@Test
	public void testSampleService() {
		assertEquals("class com.junit.spring.service.SampleServiceImpl",this.sampleService.getClass().toString());
	}
	
	@Test
	public void testSampleServiceGetAccountDescription() {
		assertTrue(sampleService.getOrderDescription().contains("Description:"));
	}
	
	@Test
	public void testSampleServiceGetAccountCode() {
		assertTrue(sampleService.getOrderStringCode().contains("Code:"));
	}
	
	@Test
	public void testSampleServiceCreateNewOrder() {
		Order newOrder = new Order();
		newOrder.setSecurityCode("XYZ");
		newOrder.setDescription("Description");
		if(newOrder != null) {
			assertThat(sampleService.createOrder(newOrder), instanceOf(Order.class));
			assertNotNull("Security isn't null", newOrder.getSecurityCode());
			assertNotNull("Description isn't not null", newOrder.getDescription());
		}
		
		assertNotNull("New Order is not null", newOrder);
		
	}
	
	@Test
	public void testSampleServiceGetOrder() {
		
		Order existingOrder = sampleService.getOrder(0);

		if(existingOrder != null) {
			assertThat(sampleService.getOrder(0), instanceOf(Order.class));
			assertNotNull("Security isn't null", existingOrder.getSecurityCode());
			assertNotNull("Description isn't null", existingOrder.getDescription());
		}
		
		assertNotNull("Object is not null", existingOrder);
	}
	
	@AfterClass
	public static void afterTest() {
		System.out.println("-----> AFTER CLASS <-----");
	}
}
