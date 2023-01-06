package vttp2022.paf.assessment.eshop.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpSession;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.services.WarehouseService;

@Controller
public class OrderController {

	@Autowired
	private WarehouseService warSvc;

	//TODO: Task 3
	@PostMapping
	public String saveOrder(@RequestBody Order order , Model model, HttpSession sess){
		
		if(warSvc.findCustomerByName(order.getName()).isEmpty()){
			JsonObjectBuilder nameErr = Json.createObjectBuilder();
            nameErr.add("error","Customer %s not found".formatted(order.getName()));
			System.out.println(nameErr.build().toString());
		}
		List<LineItem> items =  (List<LineItem>) sess.getAttribute(order.getName());
		if(items==null){
			items = new LinkedList<>();
            sess.setAttribute("cart", items);
		}
		
		warSvc.saveOrder(order);

	}
}
