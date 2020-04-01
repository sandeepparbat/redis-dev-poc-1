package com.sandeep.redis.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@RedisHash("Order")
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5721790055680900637L;

	@Id
	@Indexed
	private Long orderId;
	
	@Indexed
	private Date date;
	
	@Reference
	List<LineItem> lineItems;
	
	

	public Order(Long orderId, Date date, List<LineItem> lineItems) {
		super();
		this.orderId = orderId;
		this.date = date;
		this.lineItems = lineItems;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", date=" + date + ", lineItems=" + lineItems + "]";
	}
	
	
}
