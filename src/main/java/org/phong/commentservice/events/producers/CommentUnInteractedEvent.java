package org.phong.commentservice.events.producers;

import org.phong.commentservice.events.PublishableEvent;

import java.io.Serializable;
import java.util.UUID;

public record CommentUnInteractedEvent(UUID commentId, UUID userId) implements Serializable, PublishableEvent {
}
