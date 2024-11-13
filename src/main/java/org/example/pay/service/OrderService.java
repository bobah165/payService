package org.example.pay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.pay.dto.Order;
import org.example.pay.repository.OrderRepository;
import org.example.pay.service.producer.ProducerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

  private final OrderRepository repository;
  private final ProducerService producerService;
  private final PersonAccountService personAccountService;

  @Transactional
  public void save(Order order) {
    repository.save(order);
  }

  @Transactional
  public Order findOrder(String id) {
    return repository.findById(id)
      .orElseThrow(() -> new RuntimeException("Order with " + id + " not found"));
  }

  @Transactional
  public void createOrder(Order order) {
    personAccountService.recalculate(order.getPrice());
    order.setStatus("CREATED");
    repository.save(order);
    producerService.sendOrder(order);
  }
}
