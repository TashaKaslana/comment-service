package org.phong.commentservice.listeners;

import org.phong.commentservice.events.producers.CommentContentUpdatedEvent;
import org.phong.commentservice.events.producers.CommentCreatedEvent;
import org.phong.commentservice.events.producers.CommentDeletedEvent;
import org.phong.commentservice.events.producers.CommentsOfPostDeletedEvent;
import org.phong.commentservice.services.CommentMentionService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@RabbitListener(queues = "comment_mention_queue")
@Component
public class MentionEventListener {
    private final CommentMentionService commentMentionService;

    public MentionEventListener(CommentMentionService commentMentionService) {
        this.commentMentionService = commentMentionService;
    }

    @RabbitHandler
    public void handleMentionEvent(CommentCreatedEvent event) {
        commentMentionService.createMentions(event.commentId(), event.content());
    }

    @RabbitHandler
    public void handleCommentDeletedEvent(CommentDeletedEvent event) {
        commentMentionService.deleteAllMentionById(List.of(event.commentId()));
    }

    @RabbitHandler
    public void handleAllCommentOfPostDeletedEvent(CommentsOfPostDeletedEvent event) {
        commentMentionService.deleteAllMentionById(event.commentIdList());
    }

    @RabbitHandler
    public void handleCommentContentUpdateEvent(CommentContentUpdatedEvent event) {
        commentMentionService.updateMentions(event.commentId(), event.newContent());
    }
}
