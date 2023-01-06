package vttp2022.paf.assessment.eshop.respositories;

public class Queries {
    
    public static final String SQL_FIND_CUSTOMER_BY_NAME = "SELECT * FROM customers WHERE name = ?";
    public static final String SQL_FIND_ORDER_BY_ID = "SELECT * FROM order_store WHERE order_id = ?";
    public static final String SQL_INSERT_NEW_ORDER = "INSERT INTO order_store(order_id,name,address,email) VALUES(?, ?, ?, ?)";
    public static final String SQL_DISPATCH_ORDER = "INSERT INTO order_status(order_id,delivery_id,status,status_update) VALUES(?, ?, ?, ?)";
    public static final String SQL_GET_CUSTOMER_ORDER_STATUS = "SELECT name, count(status='dispatched') as dispatchedCount, count(status='pending') as pendingCount FROM order_store JOIN order_status WHERE name = ?";
}
