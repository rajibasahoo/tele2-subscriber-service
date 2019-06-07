package com.tele2.cache;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.tele2.dao.SubscriptionRepository;
import com.tele2.model.Subscription;

@Component
public class SubScriptionCache {
	
	private Logger logger = LoggerFactory.getLogger(SubScriptionCache.class);

    @Autowired
    SubscriptionRepository subRepository;

    @Cacheable(value = "subscriptionCache")
    public List<Subscription> getAllSubScription() {
        logger.info("Retrieving from H2 Database.");
        return subRepository.findAll();
    }
}
