package org.example.pay.service.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.pay.dto.Order;
import org.example.pay.service.OrderService;
import org.example.pay.service.PersonAccountService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerDeliveryService {

  private static final String topicCreateOrder = "${topic.consumer.delivery}";
  private static final String kafkaConsumerGroupId = "${spring.kafka.consumer.group-id}";
  private final OrderService orderService;
  private final PersonAccountService personAccountService;

  @Transactional
  @KafkaListener(topics = topicCreateOrder, groupId = kafkaConsumerGroupId, properties = {"spring.json.value.default.type=org.example.pay.dto.Order"})
  public Order createOrder(Order orderEvent) {
    log.info("Message consumed {}", orderEvent);
    orderService.save(orderEvent);
    personAccountService.setWithdrawnToFalse();
    return orderEvent;
  }
}
