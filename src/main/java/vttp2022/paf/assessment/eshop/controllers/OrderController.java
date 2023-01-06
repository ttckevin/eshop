package vttp2022.paf.assessment.eshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpSession;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.services.CustomerService;
import vttp2022.paf.assessment.eshop.services.WarehouseService;

@Controller
public class OrderController {
	//TODO: Task 3

	@Autowired
	private CustomerService cSvc;

	@Autowired
	private WarehouseService war;

	@PostMapping
	public String saveOrderToDB(@RequestBody MultiValueMap<String, String> form
	, Model model, HttpSession sess){
		//Name, order detail
		String name = form.getFirst("name");
		if(cSvc.findCustomerByName(name).isEmpty()){
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("error", "Customer %s not found".formatted(name));
			return job.build().toString();		
		}
		//Generate order id 
		String address = form.getFirst("address");
		String email = form.getFirst("email");
        List<LineItem> lineItems = (List<LineItem>)sess.getAttribute(name);
		for(LineItem i : lineItems){
			String item = form.getFirst("item");
			String quantity = form.getFirst("quantity");
			i.setItem(item);
			i.setQuantity(Integer.parseInt(quantity));
			lineItems.add(i);
		}
		Order o = new Order();
		try {
			o.setName(name);
			o.setEmail(email);
			o.setAddress(address);
			o.setLineItems(lineItems);
			if(!war.saveOrder(o)){
				return "order not saved to db...";
			};			
		} catch (Exception e) {
			String err = "error: %s".formatted(e);
			return err;	
		}

		return o.createOrder().toString();

	}
}
