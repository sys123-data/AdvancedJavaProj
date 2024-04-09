package comx.entity;

import comx.serializable.OrderID;
import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Entity
@Component
@Scope("prototype")
@Table(name = "orders")
@IdClass(OrderID.class)

public class Order {

    @Id
    @Column(name = "order_id")
    private String orderId;
    @Id
    @Column(name = "user_id")
    private String userId;
    @Id
    @Column(name = "purchase_time")
    private Date purchaseTime;
    @Id
    @JoinColumn(name = "prod_inv_id", referencedColumnName = "prod_inv_id")
    private String prodInvId;

    @Column(name = "qty")
    private int quantity;


    // Getters and setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getProdInvId() {
        return prodInvId;
    }

    public void setProdInvId(String prodInvId) {
        this.prodInvId = prodInvId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", purchaseTime=" + purchaseTime +
                ", prodInvId='" + prodInvId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}