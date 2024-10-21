package com.bkartisan.be.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bkartisan.be.Constant.OrderStatus;
import com.bkartisan.be.Dto.CartProductDTO;
import com.bkartisan.be.Dto.CreatePaymentRequestDTO;
import com.bkartisan.be.Dto.OrderBuyerDTO;
import com.bkartisan.be.Dto.OrderBuyerQueryResult;
import com.bkartisan.be.Entity.Order;
import com.bkartisan.be.Repository.OrderRepository;
import com.bkartisan.be.Util.PaymentUtil;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderProductService orderProductService;
    private CartService cartService;
    private PaymentUtil paymentUtil;

    @Autowired
    public OrderService(OrderRepository orderRepository, CartService cartService, PaymentUtil paymentUtil, 
            OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
        this.cartService = cartService;
        this.orderRepository = orderRepository;
        this.paymentUtil = paymentUtil;
    }

    public Order createAndSaveOrder(CreatePaymentRequestDTO createPaymentRequestDTO, String buyer, String seller,
            String commonId, Integer totalPrice) {
        String orderId = paymentUtil.getRandomNumber(10);

        Order order = Order.builder().orderId(orderId)
                .seller(seller)
                .paymentMethod(createPaymentRequestDTO.paymentMethod())
                .hasGift(false) // We haven't implement gift yet so set it to false
                .totalPrice(totalPrice)
                .status(OrderStatus.WAITING)
                .buyer(buyer)
                .buyerName(createPaymentRequestDTO.buyerName())
                .discountPrice(0) // We haven't implement discount yet so set it to 0
                .commonId(commonId)
                .nation(createPaymentRequestDTO.nation())
                .numPhone(createPaymentRequestDTO.numPhone())
                .address(createPaymentRequestDTO.address())
                .build();

        orderRepository.save(order);

        return order;
    }

    /**
     * Return commonId to used to set vnp_TxnRef in payment url.
     * 
     * @param createPaymentRequestDTO
     * @return commonId
     */
    @Transactional
    public String convertCartItemsToOrders(CreatePaymentRequestDTO createPaymentRequestDTO, String buyerUsername) {
        String commonId = paymentUtil.getRandomNumber(12);

        // Get Map of seller and products in buyer cart
        Map<String, List<CartProductDTO>> sellerProductsMap = cartService.getCartProductsToSellerMap(buyerUsername);

        // Convert the map into database
        sellerProductsMap.forEach((sellerUsername, products) -> {
            // Get total price
            Integer totalPrice = products.stream().mapToInt((product) -> product.getPrice() * product.getQuantity())
                    .sum();

            Order order = createAndSaveOrder(createPaymentRequestDTO, buyerUsername, sellerUsername, commonId, totalPrice);

            // Save each product in cart into OrderProduct
            products.forEach((product) -> {
                orderProductService.saveOrderProduct(order, product.getProductId(), product.getQuantity(), buyerUsername,
                        product.getNote());
            });
        });

        return commonId;
    }

    public List<OrderBuyerDTO> getBuyerOrders(String username, OrderStatus status) {
        List<OrderBuyerQueryResult> orderQueryRes = orderRepository.findByBuyerAndStatusOrderById(username, status);
        List<OrderBuyerDTO> orderBuyerDTOs = new ArrayList<>();

        // Convert OrderBuyerQueryResult to OrderBuyerDTO
        for (OrderBuyerQueryResult order : orderQueryRes) {
            if (orderBuyerDTOs.size() == 0 || !orderBuyerDTOs.get(orderBuyerDTOs.size() - 1).getOrderId().equals(order.getOrderId())) {
                orderBuyerDTOs.add(new OrderBuyerDTO(order));
            }
            else {
                orderBuyerDTOs.get(orderBuyerDTOs.size() - 1).addItem(order);
            }
        }

        return orderBuyerDTOs;
    }
}
