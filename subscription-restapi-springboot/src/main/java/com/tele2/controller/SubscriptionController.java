package com.tele2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tele2.model.Subscription;
import com.tele2.service.SubscriptionService;

				
/**
 * 
 * @author rajiba sahoo
 * This class is the controller which has four end points. It does the crud operations.
 * FallBack is applied on SaveSubcription method when subscriber == null.we can monitor api response http://localhost:8080/actuator/hystrix.stream  dashboard
 * 
 */

@RestController
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;


	@HystrixCommand(fallbackMethod = "fallBack",commandKey = "saveapi", groupKey = "saveapi")
	@PostMapping("/api/subscriptions")
	public String saveSubscription(@RequestBody Subscription subscription) {
 
		if (subscription == null) {
			throw new RuntimeException("Failed!");
		}		
		subscriptionService.saveSubscription(subscription);
		return "New Subscription Created.";
	}


	@GetMapping("/api/subscriptions")
	public List<Subscription> getAllSubscription() {			
		return subscriptionService.getAllSubScription();
	}

	@GetMapping("/api/subscriptions/{id}")
	public Subscription getSubscriptionById(@PathVariable Integer id) {				
		return subscriptionService.getSubscriptionById(id);
	}


	@PutMapping("/api/subscriptions/{id}")
	public Subscription updateSubscriptionById(@RequestBody Subscription newSubcription, @PathVariable Integer id) {		
		return subscriptionService.updateSubscriptionById(newSubcription,id);	  
	}	

	@HystrixCommand(groupKey = "saveapi")
	public String fallBack(Subscription subscription) {
		return "Fall Back Service initiated";
	}

}
