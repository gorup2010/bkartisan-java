package com.bkartisan.be.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bkartisan.be.Constant.OrderStatus;
import com.bkartisan.be.Entity.Order;

import io.lettuce.core.dynamic.annotation.Param;

import com.bkartisan.be.Dto.OrderBuyerQueryResult;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByCommonId(String commonId);

    @Modifying(clearAutomatically = true)
    @Query(value = """
                UPDATE Order o SET o.status = :status WHERE o.commonId = :commonId
            """)
    public Integer updateStatus(String commonId, OrderStatus status);

    // @Query(value = """
    //     SELECT new com.bkartisan.be.Dto.OrderBuyerQueryResult(o.orderId, o.status, o.totalPrice, o.shipPrice, o.discountPrice, o.createAt, o.paymentMethod, 
    //                     s.username, s.name, s.avatar, 
    //                     p.coverImage, p.name, op.quantity, op.quantity * p.price, p.discount)
    //     FROM Order o
    //     JOIN User s ON s.username = o.seller
    //     JOIN OrderProduct op ON op.orderId = o.orderId
    //     JOIN Product p ON p.productId = op.productId
    //     WHERE o.buyer = :buyer AND o.status = :status
    // """)
    // public List<OrderBuyerQueryResult> findByBuyerAndStatus(String buyer, OrderStatus status);

    @Query(value = """
        SELECT new com.bkartisan.be.Dto.OrderBuyerQueryResult(o.orderId, 'o.status', o.totalPrice, o.shipPrice, o.discountPrice, o.createAt, o.paymentMethod, 
                        s.username, s.name, s.avatar, 
                        p.coverImage, p.name, op.quantity, op.quantity * p.price, p.discount)
        FROM Order o
        JOIN User s ON s.username = o.seller
        JOIN OrderProduct op ON op.orderId = o.orderId
        JOIN Product p ON p.productId = op.productId
        WHERE o.buyer = :buyer and o.status = :status
    """)
    public List<OrderBuyerQueryResult> findByBuyerAndStatus(@Param("buyer") String buyer, @Param("status") OrderStatus status);
}
