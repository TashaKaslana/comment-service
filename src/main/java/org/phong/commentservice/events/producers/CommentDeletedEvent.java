package org.phong.commentservice.events.producers;

import org.phong.commentservice.events.PublishableEvent;

import java.io.Serializable;
import java.util.UUID;

public record CommentDeletedEvent(UUID commentId) implements Serializable, PublishableEvent {
}
