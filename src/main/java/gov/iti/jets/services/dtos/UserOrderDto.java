package gov.iti.jets.dtos;


import java.util.*;

public class UserOrderDto {
    private Long userId;
    private String userName;
    private List<OrderDTO> orders;

    // Default constructor
    public UserOrderDto() {}

    // Parameterized constructor
    public UserOrderDto(Long userId, String userName, List<OrderDTO> orders) {
        this.userId = userId;
        this.userName = userName;
        this.orders = orders;
    }

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", orders=" + orders +
                '}';
    }
}
