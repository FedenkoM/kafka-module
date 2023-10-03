package org.example.controller;

import org.example.dto.OrderResponseDTO;
import org.example.entity.Order;
import org.example.mapper.OrderMapper;
import org.example.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public record OrderController(ClientService clientService, OrderMapper orderMapper) {

    @PostMapping
    public OrderResponseDTO createOrder(@RequestBody Order order) {
        var savedOrder = clientService.save(order);
        clientService.publishEvent(savedOrder);
        return orderMapper.toOrderResponse(savedOrder);
    }

    @GetMapping("/{id}")
    public Order getOrderInfo(@PathVariable Long id) {
        return clientService.getOrderInfo(id);
    }

}
