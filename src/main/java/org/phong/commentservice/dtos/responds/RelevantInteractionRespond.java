package org.phong.commentservice.dtos.responds;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link org.phong.commentservice.infrastructure.persistence.models.InteractionEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record RelevantInteractionRespond(LocalDateTime createdAt, LocalDateTime updatedAt, @NotNull UUID interactorId,
                                         @NotNull @NotEmpty @NotBlank String interactionType) implements Serializable {
}