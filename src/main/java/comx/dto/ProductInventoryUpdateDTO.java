package comx.dto;

public class ProductInventoryUpdateDTO {
    private String productId;
    private Integer size;
    private Integer newQuantity;

    public ProductInventoryUpdateDTO(String productId, Integer size, Integer newQuantity) {
        this.productId = productId;
        this.size = size;
        this.newQuantity = newQuantity;
    }

    public ProductInventoryUpdateDTO() {

    }

    // Getters and setters for the properties

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(Integer newQuantity) {
        this.newQuantity = newQuantity;
    }

    // Method to check if any properties are null
    public boolean hasNullProperties() {
        return productId == null || size == null || newQuantity == null;
    }
}

