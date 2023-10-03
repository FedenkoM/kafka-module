package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Customer;
import org.example.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private Customer customer;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedOn;
}
