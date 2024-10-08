package com.bkartisan.be.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkartisan.be.Configuration.PaymentConfig;
import com.bkartisan.be.Dto.CartInformationDTO;
import com.bkartisan.be.Dto.CartProductDTO;
import com.bkartisan.be.Dto.OrderAtEachShopDTO;
import com.bkartisan.be.Util.PaymentUtil;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Service
public class PaymentService {

    private CartService cartService;
    private PaymentConfig paymentConfig;

    @Autowired
    public PaymentService(CartService cartService, PaymentConfig paymentConfig) {
        this.paymentConfig = paymentConfig;
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

    /**
     * Create payment url including:
     * - vnp_PayUrl
     * - List of parameters specified in
     * https://sandbox.vnpayment.vn/apis/docs/thanh-toan-pay/pay.html#danh-s%C3%A1ch-tham-s%E1%BB%91
     */
    public String createPaymentUrl(String txnRefCode, String username, HttpServletRequest request) {
        Map<String, String> vnpParamsMap = paymentConfig.getVNPayConfig(txnRefCode,
                username,
                cartService.getTotalPrice(username),
                PaymentUtil.getIpAddress(request));
        // build query url
        String queryUrl = PaymentUtil.getPaymentURL(vnpParamsMap, true);
        String hashData = PaymentUtil.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = PaymentUtil.hmacSHA512(paymentConfig.getVnp_SecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = paymentConfig.getVnp_PayUrl() + "?" + queryUrl;
        return paymentUrl;
    }

    public String getRedirectUrl(String vnPayResponseCode) {
        String redirectUrl = null;
        if (vnPayResponseCode != null && vnPayResponseCode.equals("00")) {
            redirectUrl = paymentConfig.getSuccessPaymentRedirectUrl();
        } else {
            redirectUrl = paymentConfig.getFailPaymentRedirectUrl();
        }

        return redirectUrl;
    }
}
