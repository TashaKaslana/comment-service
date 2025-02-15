package org.phong.commentservice.listeners;

import org.phong.commentservice.dtos.requests.CommentContentUpdateRequest;
import org.phong.commentservice.dtos.requests.CommentCreateRequest;
import org.phong.commentservice.events.consumers.PostDeletedEvent;
import org.phong.commentservice.events.consumers.UpdateCommentContentEvent;
import org.phong.commentservice.events.consumers.CreateCommentEvent;
import org.phong.commentservice.events.consumers.DeleteCommentEvent;
import org.phong.commentservice.services.CommentService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "comment_queue")
public class CommentEventListener {
    private final CommentService commentService;

    public CommentEventListener(CommentService commentService) {
        this.commentService = commentService;
    }

    @RabbitHandler
    public void handleCommentCreateEvent(CreateCommentEvent event) {
        commentService.createComment(
                new CommentCreateRequest(
                        event.parentCommentId(),
                        event.postId(),
                        event.commenterId(),
                        event.content()
                )
        );
    }

    @RabbitHandler
    public void handleCommentUpdateEvent(UpdateCommentContentEvent event) {
        commentService.updateCommentContent(
                event.commentId(),
                new CommentContentUpdateRequest(event.newContent())
        );
    }

    @RabbitHandler
    public void handleCommentDeleteEvent(DeleteCommentEvent event) {
        commentService.deleteCommentById(event.commentId());
    }

    @RabbitHandler
    public void handlePostDeletedEvent(PostDeletedEvent event) {
        commentService.deleteCommentsByPostId(event.postId());
    }
}
