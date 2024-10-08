package com.bkartisan.be.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkartisan.be.Dto.CartInformationDTO;
import com.bkartisan.be.Dto.CreatePaymentRequestDTO;
import com.bkartisan.be.Entity.Order;
import com.bkartisan.be.Repository.OrderRepository;
import com.bkartisan.be.Util.PaymentUtil;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private CartService cartService;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Return commonId to used to set vnp_TxnRef in payment url.
     * @param createPaymentRequestDTO
     * @return commonId
     */
    public String createOrder(CreatePaymentRequestDTO createPaymentRequestDTO, String username) {
        String orderId = PaymentUtil.getRandomNumber(10);
        String commonId = PaymentUtil.getRandomNumber(12);

        // TODO: get cart
        CartInformationDTO cartInfo = cartService.getCart(username);
        cartInfo.getItems().forEach(item -> {
            System.out.println(item);
        });


        // Order order = Order.builder().orderId(orderId)
        //         .seller(commonId)
        //         .paymentMethod(createPaymentRequestDTO.paymentMethod())
        //         .hasGift(false)     // We haven't implement gift yet so set it to false
        //         .totalPrice()
        //         .buyer()
        //         .buyerName(createPaymentRequestDTO.buyerName())
        //         .discountPrice(0)           // We haven't implement discount yet so set it to 0
        //         .bankCode()
        //         .commonId(commonId)
        //         .nation(createPaymentRequestDTO.nation())
        //         .phoneNum()
        //         .isReturn()
        //         .isFinished()
        //         .build();

        return commonId;
    }

}
