package org.phong.commentservice.events.producers;

import org.phong.commentservice.events.PublishableEvent;

import java.io.Serializable;
import java.util.UUID;

public record CommentCreatedEvent(UUID commentId, UUID postId, String content) implements Serializable, PublishableEvent {
}
