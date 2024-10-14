package com.bkartisan.be.Converter;

import org.springframework.stereotype.Component;

import com.bkartisan.be.Constant.OrderStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Component
@Converter
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {
    @Override
    public String convertToDatabaseColumn(OrderStatus status) {
        if (status == null) {
            return null;
        }
        return status.toString();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String dbData) {
        return OrderStatus.getEnumConstantFromString(dbData);
    }
}
