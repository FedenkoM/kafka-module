package org.example.controller;

import org.example.entity.Product;
import org.example.service.ClientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public record ProductController(ClientService clientService) {

    @PostMapping
    public Product createOrder(@RequestBody Product product) {
        clientService.publishEvent(product);
        return product;
    }

}
