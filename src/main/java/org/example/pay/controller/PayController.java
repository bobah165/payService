package org.example.pay.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.pay.dto.Order;
import org.example.pay.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pay")
@Slf4j
public class PayController {

  private final OrderService service;

  @PostMapping("/save")
  public ResponseEntity createOrder(@RequestBody Order order) {
    log.info("Save order");
    service.createOrder(order);
    return ResponseEntity.ok("Order with name " + order.getName() + " and sum = " + order.getPrice() + " was created ");
  }

  @GetMapping("/status/{orderId}")
  public ResponseEntity getCurrentStatus(@PathVariable("orderId") String orderId) {
    var order = service.findOrder(orderId);
    return ResponseEntity.ok("Order with name " + order.getName() + " has status " + order.getStatus());
  }
}
