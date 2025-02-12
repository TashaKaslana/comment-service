package org.phong.commentservice.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link org.phong.commentservice.infrastructure.persistence.models.InteractionEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record InteractionDeleleRequest(@NotNull UUID commentId, @NotNull UUID interactorId) implements Serializable {
}