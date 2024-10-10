package com.bkartisan.be.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkartisan.be.Entity.OrderProduct;
import com.bkartisan.be.Repository.OrderProductRepository;

@Service
public class OrderProductService {
    
    private OrderProductRepository orderProductRepository;

    @Autowired
    public OrderProductService(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    public void saveOrderProduct(String commonId, Integer productId, Integer quantity, String buyer, String note) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setCommonId(commonId);
        orderProduct.setProductId(productId);
        orderProduct.setQuantity(quantity);
        orderProduct.setBuyer(buyer);
        orderProduct.setNote(note);
        orderProductRepository.save(orderProduct);
    }

}
