package org.phong.commentservice.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link org.phong.commentservice.infrastructure.persistence.models.CommentEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CommentContentUpdateRequest(@NotNull @Size @NotEmpty @NotBlank String content) implements Serializable {
  }