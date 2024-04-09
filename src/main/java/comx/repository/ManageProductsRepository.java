package comx.repository;

import comx.dto.ProductInventoryDTO;
import comx.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManageProductsRepository extends JpaRepository<Order, Long> {

    @Query("SELECT new comx.dto.ProductInventoryDTO(p.productId, p.brand, p.model, pi.size, pi.quantity, p.imageUrl, p.price) " +
            "FROM Product p JOIN ProductInventory pi ON p.productId = pi.product.productId")
    List<ProductInventoryDTO> getProductInventory();



}
