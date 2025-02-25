package org.phong.commentservice.infrastructure.persistence.repositories;

import jakarta.validation.constraints.NotNull;
import org.phong.commentservice.infrastructure.persistence.models.CommentEntity;
import org.phong.commentservice.infrastructure.persistence.models.InteractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InteractionRepository extends JpaRepository<InteractionEntity, UUID> {
    void deleteByInteractorIdAndCommentIdAllIgnoreCase(UUID interactorId, UUID commentId);

    InteractionEntity findInteractionEntityByInteractorIdAndCommentIdAllIgnoreCase(@NotNull UUID interactorId, @NotNull UUID commentId);

    void deleteAllByCommentId(UUID commentId);

    List<InteractionEntity> findAllByComment(CommentEntity comment);
}
