package org.phong.commentservice.listeners;

import org.phong.commentservice.dtos.requests.InteractionCreateRequest;
import org.phong.commentservice.dtos.requests.InteractionDeleleRequest;
import org.phong.commentservice.events.producers.CommentDeletedEvent;
import org.phong.commentservice.events.producers.CommentInteractedEvent;
import org.phong.commentservice.events.producers.CommentUnInteractedEvent;
import org.phong.commentservice.events.producers.CommentsOfPostDeletedEvent;
import org.phong.commentservice.services.InteractionService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = "comment_interaction_queue")
@Component
public class InteractionEventListener {
    private final InteractionService interactionService;

    public InteractionEventListener(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @RabbitHandler
    public void handleCommentDeletedEvent(CommentDeletedEvent event) {
        interactionService.deleteAllInteractionsByCommentId(event.commentId());
    }

    @RabbitHandler
    public void handleCommentsOfPostDeletedEvent(CommentsOfPostDeletedEvent event) {
        interactionService.deleteAllInteractionsByCommentIdList(event.commentIdList());
    }

    @RabbitHandler
    public void handleInteractionRemovalEvent(CommentUnInteractedEvent event) {
        interactionService.deleteInteraction(new InteractionDeleleRequest(event.commentId(), event.userId()));
    }

    @RabbitHandler
    public void handleInteractEvent(CommentInteractedEvent event) {
        interactionService.createInteraction(
                new InteractionCreateRequest(
                        event.commentId(),
                        event.userId(),
                        event.interactionType()
                )
        );
    }
}
