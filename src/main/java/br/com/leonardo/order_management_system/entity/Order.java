package br.com.leonardo.order_management_system.entity;

import br.com.leonardo.order_management_system.enums.DeliveryType;
import br.com.leonardo.order_management_system.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItemList = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String orderNumber;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime orderDate;

    @NotNull
    @Column(nullable = false)
    private LocalDate deliveryDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryType deliveryType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @NotNull
    @DecimalMin("0.0")
    @Column(nullable = false, precision = 9, scale = 2)
    private BigDecimal deliveryFee;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @DecimalMin("0.0")
    @Column(nullable = false, precision = 9, scale = 2)
    private BigDecimal totalValue;

    public Order(){}

    public Order(List<OrderItem> orderItemList, String orderNumber, LocalDateTime orderDate, LocalDate deliveryDate, DeliveryType deliveryType, OrderStatus orderStatus, BigDecimal deliveryFee, User user, Address address) {
        this.orderItemList = orderItemList;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.deliveryType = deliveryType;
        this.orderStatus = orderStatus;
        this.deliveryFee = deliveryFee;
        this.user = user;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }
}
