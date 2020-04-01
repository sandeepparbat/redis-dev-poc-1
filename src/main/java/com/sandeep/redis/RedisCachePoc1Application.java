package com.sandeep.redis;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sandeep.redis.model.LineItem;
import com.sandeep.redis.model.Order;
import com.sandeep.redis.repository.ItemLineRepository;
import com.sandeep.redis.repository.OrderRepository;

import lombok.extern.java.Log;

@Log
@SpringBootApplication
public class RedisCachePoc1Application implements CommandLineRunner{

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ItemLineRepository itemLineRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(RedisCachePoc1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Long orderId = generateId();
		
		List<LineItem> lineItems = Arrays.asList(
				new LineItem(orderId,generateId(),"plunger"),
				new LineItem(orderId,generateId(),"soap"),
				new LineItem(orderId,generateId(),"Cofee Mug"),
				new LineItem(orderId,generateId(),"Mobile"),
				new LineItem(orderId,generateId(),"Wire"));
		
		lineItems.stream().map(itemLineRepository :: save).forEach(System.out::println);
		
		Order order = new Order(orderId, new Date(), lineItems);
		orderRepository.save(order);
		
		Collection<Order> order2 = orderRepository.findByDate(order.getDate());
		order2.forEach(System.out::println);
		
	}
	
	private long generateId() {
		long tmp = new Random().nextLong();
		return Math.max(tmp, tmp*-1);
	}
	
}
