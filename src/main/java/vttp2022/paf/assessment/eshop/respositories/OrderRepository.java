package vttp2022.paf.assessment.eshop.respositories;

import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;

@Repository
public class OrderRepository {
	// TODO: Task 3

	@Autowired
	private JdbcTemplate template;

	public boolean saveOrder(Order order){
		return template.update(SQL_INSERT_NEW_ORDER,order.generateId(),order.getName(),
		order.getAddress(),order.getEmail())>0;
	}

	public Order checkOrderId(String orderId){

		SqlRowSet srs =  template.queryForRowSet(SQL_FIND_ORDER_BY_ID, orderId);
		Order o = new Order();
		if(srs.next()){
			o.setName(srs.getString("name"));
			o.setAddress(srs.getString("address"));
			o.setEmail(srs.getString("email"));
			o.setOrderId(srs.getString("order_id"));
		}
		return o;
	}

	public boolean dispatchOrder(Order order){
		return template.update(SQL_DISPATCH_ORDER,order.getOrderId(),order.generateId(),order.getStatus(),new Date().toString())>0;
	}

	public OrderStatus getCustomerOrderStatus(String name){
		SqlRowSet srs = template.queryForRowSet(SQL_GET_CUSTOMER_ORDER_STATUS,name);
		OrderStatus o = new OrderStatus();
		if(srs.next()){
			o.setName(name);
			o.setPendingCount(srs.getString("pending"));
			o.setDispatchedCount(srs.getString("dispatched"));
		}
		return o;
	}
}
