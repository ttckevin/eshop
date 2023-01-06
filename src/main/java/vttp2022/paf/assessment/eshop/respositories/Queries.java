package vttp2022.paf.assessment.eshop.respositories;

public class Queries {
    
    public static final String SQL_FIND_CUSTOMER_BY_NAME = "SELECT * FROM customers WHERE name = ?";

    public static final String SQL_INSERT_NEW_ORDER = "INSERT INTO order_store(order_id,deliveryId,name,address,email,status,orderDate) VALUES(?, ?, ?, ?, ?, ?, ?)";

}
