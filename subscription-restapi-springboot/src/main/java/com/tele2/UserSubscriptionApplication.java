package com.tele2;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.event.EventListener;

import com.tele2.dao.SubscriptionRepository;
import com.tele2.model.Subscription;


@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard

public class UserSubscriptionApplication  {

	
	@Autowired
	SubscriptionRepository repository;
	
	private Logger logger = LoggerFactory.getLogger(UserSubscriptionApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(UserSubscriptionApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void  loadData() {
		
		Subscription subscriber =new Subscription();
		subscriber.setId(1001);
		subscriber.setName("Customer 1");
		subscriber.setMonthlyPrice(BigDecimal.valueOf(300));
		subscriber.setLastUpdate(new Date());
		repository.save(subscriber);
		
		logger.info("Data Populated On Start up.");
	}	
		

}
