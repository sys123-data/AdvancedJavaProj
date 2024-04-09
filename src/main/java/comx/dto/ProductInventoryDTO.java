package comx.dto;


public class ProductInventoryDTO {
    private String productId;
    private String brand;
    private String model;
    private String imageUrl;
    private Double price;
    private Integer size;
    private Integer quantity;

    public ProductInventoryDTO(String productId, String brand, String model, Integer size, Integer quantity, String imageUrl, Double price) {
        this.productId = productId;
        this.brand = brand;
        this.model = model;
        this.size = size;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public ProductInventoryDTO() {

    }

    // Getters and setters

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    // Method to check if any properties are null
    public boolean hasNullProperties() {
        return productId == null || brand == null || model == null || imageUrl == null || price == null || size == null || quantity == null;
    }
}


