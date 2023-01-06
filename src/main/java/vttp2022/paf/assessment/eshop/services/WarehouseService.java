package vttp2022.paf.assessment.eshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;

@Service
public class WarehouseService {

	@Autowired
	private OrderRepository or;
	// You cannot change the method's signature
	// You may add one or more checked exceptions
	public OrderStatus dispatch(Order order) {
		// TODO: Task 4
		OrderStatus os = new OrderStatus();
		os.setOrderId(order.getOrderId());
		String id = order.generateId();
		if(!or.dispatchOrder(order)){
			os.setStatus("Pending");
		}else{
			os.setDeliveryId(id);
			os.setStatus("dispatched");
		}
		return os;
	}
	
	public boolean saveOrder(Order order){
		return or.saveOrder(order);
	}

	public Order checkOrderId(String orderId){
		return or.checkOrderId(orderId);
	}

	public JsonObject getCustomerOrderStatus(String name){
		OrderStatus os = or.getCustomerOrderStatus(name);
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("name", name);
		job.add("dispatched", os.getDispatchedCount());
		job.add("pending", os.getPendingCount());
		return job.build();
	}
}
