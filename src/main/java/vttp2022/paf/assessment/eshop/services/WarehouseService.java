package vttp2022.paf.assessment.eshop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;

@Service
public class WarehouseService {

	@Autowired
	private CustomerRepository custRepo; 

	@Autowired
	private OrderRepository orderRepo;
	// You cannot change the method's signature
	// You may add one or more checked exceptions
	public OrderStatus dispatch(Order order) {
		// TODO: Task 4
		

		return null;
	}

	//a) Check if customer is valid
	public Optional<Customer> findCustomerByName(String name){
		return custRepo.findCustomerByName(name);
	}

	public boolean checkOrderItem(Order order){
		return orderRepo.checkOrderItem(order);
	}

	public boolean saveOrder(Order order){
		if(checkOrderItem(order)){
			return orderRepo.saveOrder(order);
		}
		return false;
	}
}
