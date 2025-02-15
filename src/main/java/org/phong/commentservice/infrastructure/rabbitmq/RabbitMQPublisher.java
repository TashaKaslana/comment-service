package org.phong.commentservice.infrastructure.rabbitmq;

import org.phong.commentservice.events.PublishableEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQPublisher {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public <T extends PublishableEvent> void sendMessage(String routingKey, T message) {
        String EXCHANGE_NAME = "comment_exchange";

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, message);
    }
}
