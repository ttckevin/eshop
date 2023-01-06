package vttp2022.paf.assessment.eshop.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.Order;
import static vttp2022.paf.assessment.eshop.respositories.Queries.*;
@Repository
public class OrderRepository {
	// TODO: Task 3

	@Autowired
	private JdbcTemplate template;

	public boolean saveOrder(Order order){
		return template.update(SQL_INSERT_NEW_ORDER,order.getOrderId(),order.getDeliveryId(),order.getName(),
		order.getAddress(),order.getEmail(),order.getStatus(),order.getOrderDate())>0;
	}

	public boolean checkOrderItem(Order order){
		if(order.getLineItems()==null){
			return false;
		}
		return true;
	}

}
