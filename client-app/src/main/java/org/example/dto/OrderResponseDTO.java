package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private CustomerDTO customer;
    private List<OrderItemDTO> orderItems;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedOn;
}
