package comx.serializable;

import java.io.Serializable;
import java.sql.Date;

public class OrderID implements Serializable {
    private String orderId;
    private String userId;
    private Date purchaseTime;
    private String prodInvId;

    // Constructors, getters, setters, equals, and hashCode methods

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
}
