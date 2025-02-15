package org.phong.commentservice.infrastructure.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// consider to using dynamic binding by loops
@Configuration
public class RabbitMQConfig {
    private static final String QUEUE_NAME = "comment_queue";
    private static final String EXCHANGE_NAME = "comment_exchange";

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public String getQueueName() {
        return QUEUE_NAME;
    }

    @Bean
    public Queue commentQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Queue commentInteractionQueue() {
        return new Queue("comment_interaction_queue", true);
    }

    @Bean
    public Queue commentMentionQueue() {
        return new Queue("comment_mention_queue", true);
    }

    @Bean
    public TopicExchange commentExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindComment(Queue commentQueue, TopicExchange commentExchange) {
        return BindingBuilder.bind(commentQueue).to(commentExchange).with("comment.#");
    }

    @Bean
    public Binding mentionBinding(Queue commentMentionQueue, TopicExchange commentExchange) {
        return BindingBuilder.bind(commentMentionQueue).to(commentExchange).with("mention.#");
    }

    @Bean
    public Binding interactionBinding(Queue commentInteractionQueue, TopicExchange commentExchange) {
        return BindingBuilder.bind(commentInteractionQueue).to(commentExchange).with("interaction.#");
    }
}
