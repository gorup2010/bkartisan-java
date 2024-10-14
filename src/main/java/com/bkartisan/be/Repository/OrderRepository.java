package com.bkartisan.be.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bkartisan.be.Constant.OrderStatus;
import com.bkartisan.be.Entity.Order;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByCommonId(String commonId);

    @Modifying(clearAutomatically = true)
    @Query(value = """
        UPDATE orders o SET o.status = :status WHERE o.commonId = :commonId
    """, nativeQuery = true)
    public Integer updateStatus(String commonId, OrderStatus status);
}
