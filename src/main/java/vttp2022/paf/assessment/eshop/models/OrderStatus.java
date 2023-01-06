package vttp2022.paf.assessment.eshop.models;

import java.util.UUID;

import jakarta.json.Json;
import jakarta.json.JsonObject;

// DO NOT CHANGE THIS CLASS
public class OrderStatus {

	private String orderId;
	private String deliveryId = "";
	private String status = "pending"; // or "dispatched"

	public String getOrderId() { return this.orderId; }
	public void setOrderId(String orderId) { this.orderId = orderId; }

	public String getDeliveryId() { return this.deliveryId; }
	public void setDeliveryId(String deliveryId) { this.deliveryId = deliveryId; }

	public String getStatus() { return this.status; }
	public void setStatus(String status) { this.status = status; }

	public String generateDeliveryId(){
		String order_id = UUID.randomUUID().toString().substring(0,8);
	  return order_id;
  }

	public JsonObject updateStatusTaskFour(Order order){
		return Json.createObjectBuilder()
			.add("orderId", order.getOrderId())
			.add("deliveryId", generateDeliveryId())
			.build();
	}

	public JsonObject updateStatusTaskFiveSuccess(Order order){
		return Json.createObjectBuilder()
			.add("orderId", order.getOrderId())
			.add("deliveryId", generateDeliveryId())
			.add("status", "dispatched")
			.build();
	}
	public JsonObject updateStatusTaskFiveFail(Order order){
		return Json.createObjectBuilder()
			.add("orderId", order.getOrderId())
			.add("status", "Pending")
			.build();
	}
}
