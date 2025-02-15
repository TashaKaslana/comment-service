package org.phong.commentservice.events.consumers;

import java.io.Serializable;
import java.util.UUID;

public record DeleteCommentEvent(UUID commentId) implements Serializable {
}
