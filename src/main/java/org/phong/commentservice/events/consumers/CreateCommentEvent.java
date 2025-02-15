package org.phong.commentservice.events.consumers;

import java.io.Serializable;
import java.util.UUID;

public record CreateCommentEvent(UUID parentCommentId,
                                 UUID postId,
                                 UUID commenterId,
                                 String content) implements Serializable {
}
