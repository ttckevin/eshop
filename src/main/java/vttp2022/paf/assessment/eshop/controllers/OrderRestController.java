package vttp2022.paf.assessment.eshop.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;
import vttp2022.paf.assessment.eshop.services.WarehouseService;

@RestController
public class OrderRestController {
    
    @Autowired
    private WarehouseService wh;

    @PostMapping("{name}")
    public ResponseEntity<String> createOrder(@PathVariable String name){
        Optional<Customer> nameCheck = wh.findCustomerByName(name);

        if(nameCheck.isEmpty()){
            JsonObjectBuilder nameErr = Json.createObjectBuilder();
            nameErr.add("error","Customer %s not found".formatted(name));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(nameErr.build().toString());
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(name);
    }

    @PostMapping("{orderId}")
    public ResponseEntity<String> dispatchOrder(@PathVariable String orderId, @RequestBody Order order){
        		
        Optional<Customer> nameCheck = wh.findCustomerByName(order.getName());

        if(nameCheck.isEmpty()){
            JsonObjectBuilder nameErr = Json.createObjectBuilder();
            nameErr.add("error","Customer %s not found".formatted(order.getName()));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(nameErr.build().toString());
        }
        if(!wh.checkOrderItem(order)){
            JsonObjectBuilder orderErr = Json.createObjectBuilder();
            orderErr.add("error","Customer %s Item not found".formatted(order.getName()));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(orderErr.build().toString());
        }

        JsonObject orderToBe = order.createOrder();
        OrderStatus dispatch = new OrderStatus();
        if(!orderToBe.isEmpty()){
            if(wh.saveOrder(order))
                dispatch.updateStatusTaskFour(order);
            else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("error: Internal Server Error");
            } 
        }
        return ResponseEntity.ok().body(dispatch.toString());
    }
    
    
}
