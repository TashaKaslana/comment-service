package org.phong.commentservice.listeners;

import org.phong.commentservice.events.producers.CommentContentUpdatedEvent;
import org.phong.commentservice.events.producers.CommentCreatedEvent;
import org.phong.commentservice.events.producers.CommentDeletedEvent;
import org.phong.commentservice.events.producers.CommentsOfPostDeletedEvent;
import org.phong.commentservice.infrastructure.rabbitmq.RabbitMQPublisher;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "comment_internal_queue")
public class CommentMediatorListener {
    private final RabbitMQPublisher rabbitMQPublisher;

    public CommentMediatorListener(RabbitMQPublisher rabbitMQPublisher) {
        this.rabbitMQPublisher = rabbitMQPublisher;
    }

    @RabbitHandler
    public void handleCommentDeleted(CommentDeletedEvent event) {
        rabbitMQPublisher.sendMessage("mention.deleted", event);
        rabbitMQPublisher.sendMessage("interaction.deleted", event);
    }

    @RabbitHandler
    public void handleCommentCreated(CommentCreatedEvent event) {
        rabbitMQPublisher.sendMessage("mention.created", event);
    }

    @RabbitHandler
    public void handleCommentContentUpdated(CommentContentUpdatedEvent event) {
        rabbitMQPublisher.sendMessage("mention.updated", event);
    }

    @RabbitHandler
    public void handleCommentsOfPostDeleted(CommentsOfPostDeletedEvent event) {
        rabbitMQPublisher.sendMessage("mention.deleted", event);
        rabbitMQPublisher.sendMessage("interaction.deleted", event);
    }
}
