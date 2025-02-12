package org.phong.commentservice.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link org.phong.commentservice.infrastructure.persistence.models.InteractionEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record InteractionCreateRequest(@NotNull UUID commentId, @NotNull UUID interactorId,
                                       @NotNull @Size(max = 32) @NotEmpty @NotBlank String interactionType) implements Serializable {
}