package org.phong.commentservice.events.consumers;

import java.io.Serializable;
import java.util.UUID;

public record UpdateCommentContentEvent(UUID commentId, String newContent) implements Serializable {
}
