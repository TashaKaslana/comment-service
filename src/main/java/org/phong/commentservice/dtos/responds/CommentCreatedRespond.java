package org.phong.commentservice.dtos.responds;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link org.phong.commentservice.infrastructure.persistence.models.CommentEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CommentCreatedRespond(@PastOrPresent LocalDateTime createdAt, @NotNull UUID id) implements Serializable {
}