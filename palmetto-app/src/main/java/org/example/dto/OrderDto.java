package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private OrderStatus status;
    private String comment;
    private List<OrderItemDto> orderItems;
}
