package org.phong.commentservice.listeners;

import org.phong.commentservice.events.producers.CommentContentUpdatedEvent;
import org.phong.commentservice.events.producers.CommentCreatedEvent;
import org.phong.commentservice.events.producers.CommentDeletedEvent;
import org.phong.commentservice.events.producers.CommentsOfPostDeletedEvent;
import org.phong.commentservice.infrastructure.rabbitmq.RabbitMQPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class CommentMediatorListener {
    private final RabbitMQPublisher rabbitMQPublisher;

    public CommentMediatorListener(RabbitMQPublisher rabbitMQPublisher) {
        this.rabbitMQPublisher = rabbitMQPublisher;
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleCommentDeleted(CommentDeletedEvent event) {
        rabbitMQPublisher.sendMessage("mention.deleted", event);
        rabbitMQPublisher.sendMessage("interaction.deleted", event);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleCommentCreated(CommentCreatedEvent event) {
        rabbitMQPublisher.sendMessage("mention.created", event);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleCommentContentUpdated(CommentContentUpdatedEvent event) {
        rabbitMQPublisher.sendMessage("mention.updated", event);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleCommentsOfPostDeleted(CommentsOfPostDeletedEvent event) {
        rabbitMQPublisher.sendMessage("mention.deleted", event);
        rabbitMQPublisher.sendMessage("interaction.deleted", event);
    }
}
