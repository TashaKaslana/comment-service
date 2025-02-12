package org.phong.commentservice.dtos.responds;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link org.phong.commentservice.infrastructure.persistence.models.CommentEntity}
 */
public record CommentEntityPartialRespond(@PastOrPresent LocalDateTime createdAt,
                                          @NotNull @PastOrPresent LocalDateTime updatedAt, @NotNull UUID id,
                                          UUID parentCommentId, @NotNull UUID postId, @NotNull UUID commenterId,
                                          @NotNull @Size(max = 1000) @NotEmpty @NotBlank String content) implements Serializable {
}