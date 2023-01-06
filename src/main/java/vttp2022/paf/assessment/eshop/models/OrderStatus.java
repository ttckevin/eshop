package vttp2022.paf.assessment.eshop.models;

// DO NOT CHANGE THIS CLASS
public class OrderStatus {

	private String orderId;
	private String deliveryId = "";
	private String status = "pending"; // or "dispatched"
	private String pendingCount;
	private String dispatchedCount;
	private String name;
	
	public String getPendingCount() {
		return pendingCount;
	}
	public void setPendingCount(String pendingCount) {
		this.pendingCount = pendingCount;
	}
	public String getDispatchedCount() {
		return dispatchedCount;
	}
	public void setDispatchedCount(String dispatchedCount) {
		this.dispatchedCount = dispatchedCount;
	}
	public String getOrderId() { return this.orderId; }
	public void setOrderId(String orderId) { this.orderId = orderId; }

	public String getDeliveryId() { return this.deliveryId; }
	public void setDeliveryId(String deliveryId) { this.deliveryId = deliveryId; }

	public String getStatus() { return this.status; }
	public void setStatus(String status) { this.status = status; }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}
