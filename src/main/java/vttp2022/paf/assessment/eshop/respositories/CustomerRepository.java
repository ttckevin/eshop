package vttp2022.paf.assessment.eshop.respositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import vttp2022.paf.assessment.eshop.models.Customer;
import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

@Repository
public class CustomerRepository {

	@Autowired
	private JdbcTemplate template;
	// You cannot change the method's signature
	public Optional<Customer> findCustomerByName(String name) {
		// TODO: Task 3 
		Customer customer = new Customer();		
		SqlRowSet srs = template.queryForRowSet(SQL_FIND_CUSTOMER_BY_NAME, name);
		while(srs.next()){
			customer = Customer.create(srs);
		}			
		if(customer!=null){
			return Optional.of(customer);
		}

		return Optional.of(customer);
	}
}
