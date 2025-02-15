package org.phong.commentservice.events.consumers;

import org.phong.commentservice.events.PublishableEvent;

import java.io.Serializable;
import java.util.UUID;

public record PostDeletedEvent(UUID postId) implements Serializable, PublishableEvent {
}
