package org.phong.commentservice.events.producers;

import org.phong.commentservice.events.PublishableEvent;

import java.io.Serializable;
import java.util.UUID;

    public record CommentContentUpdatedEvent(UUID commentId, String newContent) implements Serializable, PublishableEvent {
}
