package gov.iti.jets.dtos;


public class OrderDTO {
    private Long orderId;
    private String orderDetails;

    // Default constructor
    public OrderDTO() {}

    // Parameterized constructor
    public OrderDTO(Long orderId, String orderDetails) {
        this.orderId = orderId;
        this.orderDetails = orderDetails;
    }

    // Getters and setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", orderDetails='" + orderDetails + '\'' +
                '}';
    }
}
