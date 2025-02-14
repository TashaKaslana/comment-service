package org.phong.commentservice.infrastructure.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String EXCHANGE_NAME = "comment_exchange";

    public RabbitMQPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public <T> void sendCommentMessage(String exchangeName, String routingKey, T message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
