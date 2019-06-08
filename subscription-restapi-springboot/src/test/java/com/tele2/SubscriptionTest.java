package com.tele2;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.tele2.dao.SubscriptionRepository;
import com.tele2.model.Subscription;
import com.tele2.service.SubscriptionService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SubscriptionTest {
	
	@InjectMocks
    private SubscriptionService subscriptionService;
   
	@Mock
    private SubscriptionRepository repository;
    private Subscription subscription;
    
    @Before
    public void setUp() {
        subscription = new Subscription();
        subscription.setId(11);
        subscription.setMonthlyPrice(new BigDecimal(112.50));
        subscription.setLastUpdate(new Date());
        subscription.setName("Custormer1");
    }
    
	@Test
    public void testFindSubscriptionById() {
        Mockito.when(this.repository.findById(subscription.getId())).thenReturn(Optional.ofNullable(subscription));
        Subscription subscription = subscriptionService.getSubscriptionById(11);
        assertNotNull(subscription);
    }

	@Test
    public void testGetAllSubscriptions() {
        List<Subscription> subscriptionList = new ArrayList<>();
        subscriptionList.add(subscription);
        Mockito.when(this.repository.findAll()).thenReturn(subscriptionList);
        List<Subscription> subscription =  subscriptionService.getAllSubScription();
        assertNotNull(subscription);
    }

}
