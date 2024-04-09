package comx.repository;

import comx.entity.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInventoryRepository extends JpaRepository<ProductInventory, String> {
    // You can define custom query methods here if needed
    @Query("SELECT pi FROM ProductInventory pi WHERE pi.product.productId = :productId AND pi.size = :size")
    ProductInventory findByProductIdAndSize(@Param("productId") String productId, @Param("size") Integer size);
}