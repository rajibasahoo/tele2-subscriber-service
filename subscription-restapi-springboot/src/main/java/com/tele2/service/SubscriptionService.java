package com.tele2.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tele2.cache.SubScriptionCache;
import com.tele2.controller.SubscriptionNotFoundException;
import com.tele2.dao.SubscriptionRepository;
import com.tele2.model.Subscription;

/**
 * 
 * @author rajiba sahoo
 * This class is the service layer which has all the required method to invoke dao layer and for getAllSubscription EHCache is applied.
 * 
 */

@Service
public class SubscriptionService {

	
	private Logger logger = LoggerFactory.getLogger(SubscriptionService.class);

	@Autowired
	private SubscriptionRepository repository;	

	@Autowired
	SubScriptionCache subscriptionCache;	

	public void saveSubscription(Subscription subscription) {
		logger.debug("Inside service layer saveSubscription method.");
		repository.save(subscription);		
	}
	public List<Subscription> getAllSubScription() {
		logger.debug("Inside service layer getAllSubScription method.");
		return subscriptionCache.getAllSubScription();
	}
	public Subscription getSubscriptionById(Integer id) {
		logger.debug("Inside service layer getSubscriptionById method.");
		return repository.findById(id).orElseThrow(() -> new SubscriptionNotFoundException(id));
	}
	public Subscription updateSubscriptionById(Subscription newSubcription, Integer id) {
		logger.debug("Inside service layer updateSubscriptionById method.");
		return repository.findById(id)
				.map(subscription -> {
					subscription.setMonthlyPrice(newSubcription.getMonthlyPrice());	        
					return repository.save(subscription);
				}).orElseThrow(() -> new SubscriptionNotFoundException(id));

	}
	
}
