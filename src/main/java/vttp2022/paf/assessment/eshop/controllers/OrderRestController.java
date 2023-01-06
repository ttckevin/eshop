package vttp2022.paf.assessment.eshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpSession;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;
import vttp2022.paf.assessment.eshop.services.WarehouseService;

@RestController
public class OrderRestController {
    
    @Autowired
    private WarehouseService war;

    @PostMapping
    @RequestMapping(path="http://paf.chuklee.com/dispatch/{orderId}")
    public ResponseEntity<String> dispatchOrder(@PathVariable String orderId, HttpSession sess){
        
        //Validate orderId
        Order o = war.checkOrderId(orderId);
        if(o!=null){
            List<LineItem> lineItems = (List<LineItem>)sess.getAttribute(o.getName());
            o.setLineItems(lineItems);
            o.createOrder();
        }
        JsonObjectBuilder job = Json.createObjectBuilder();
        OrderStatus os = war.dispatch(o);
        if(os.getStatus()!="Pending"){
        job.add("deliveryId", os.getDeliveryId());
        }
        job.add("orderId", os.getOrderId());
        job.add("status", os.getStatus());

        return ResponseEntity.status(HttpStatus.OK).body(job.build().toString());
    }

    @GetMapping
    @RequestMapping(path="/api/order/{name}/status")
    public ResponseEntity<String> getTotalNumOfDispatchedAndPendingOrders(@PathVariable String name){
        JsonObject obj = war.getCustomerOrderStatus(name);

        return ResponseEntity.status(HttpStatus.OK).body(obj.toString());
    }
}
