package org.example.mapper;

import org.example.dto.OrderResponseDTO;
import org.example.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponseDTO toOrderResponse(Order order);

}
