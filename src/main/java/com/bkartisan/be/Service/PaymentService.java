package com.bkartisan.be.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkartisan.be.Dto.CartInformationDTO;
import com.bkartisan.be.Dto.CartProductDTO;
import com.bkartisan.be.Dto.OrderAtEachShopDTO;
import com.bkartisan.be.Entity.CartItem;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Service
public class PaymentService {
    
    private CartService cartService;

    @Autowired
    public PaymentService(CartService cartService) {
        this.cartService = cartService;
    }

    public List<OrderAtEachShopDTO> checkoutOrder(String username) {
        CartInformationDTO cartInfo = cartService.getCart(username);
        List<CartProductDTO> productsInCart = cartInfo.getItems();

        // Map products into respective seller
        Map<String, List<CartProductDTO>> sellerProductsMap = new HashMap<>();

        for (CartProductDTO product : productsInCart) {
            if (!sellerProductsMap.containsKey(product.getSellerUsername())) {
                sellerProductsMap.put(product.getSellerUsername(), new ArrayList<CartProductDTO>());
            }
            sellerProductsMap.get(product.getSellerUsername()).add(product);
        }

        // Convert the map into a list of OrderAtEachShopDTO
        List<OrderAtEachShopDTO> orders = new ArrayList<>();

        for (Map.Entry<String, List<CartProductDTO>> entry : sellerProductsMap.entrySet()) {
            List<CartProductDTO> products = entry.getValue();
            OrderAtEachShopDTO order = new OrderAtEachShopDTO(products);
            orders.add(order);
        }

        return orders;
    }

}
