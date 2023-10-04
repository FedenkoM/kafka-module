package org.example.mapper;

import org.example.dto.CustomerDTO;
import org.example.dto.OrderItemDTO;
import org.example.dto.OrderResponseDTO;
import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.OrderItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponseDTO toOrderResponse(Order order);

    CustomerDTO toCustomerDTO(Customer customer);

    List<OrderItemDTO> toOrderItemsDTO(List<OrderItem> orderItems);

    OrderItemDTO toOrderItemDTO(OrderItem orderItem);

}
