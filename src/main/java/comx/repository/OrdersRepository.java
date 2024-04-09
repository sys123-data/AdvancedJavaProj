package comx.repository;

import comx.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.purchaseTime BETWEEN :startTime AND :endTime AND o.prodInvId LIKE %:size")
    List<Order> findByPurchaseTimeBetweenAndProdInvIdEndingWith(Date startTime, Date endTime, String size);

}
