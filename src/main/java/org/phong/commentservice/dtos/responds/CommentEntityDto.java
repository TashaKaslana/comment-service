package org.phong.commentservice.dtos.responds;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.phong.commentservice.infrastructure.persistence.models.CommentMentionEntity;
import org.phong.commentservice.infrastructure.persistence.models.InteractionEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link org.phong.commentservice.infrastructure.persistence.models.CommentEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CommentEntityDto(LocalDateTime createdAt, LocalDateTime updatedAt, UUID id, UUID parentCommentId,
                               UUID postId, UUID commenterId, String content, List<InteractionEntity> interactions,
                               List<CommentMentionEntity> mentions) implements Serializable {
}