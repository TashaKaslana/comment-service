package org.phong.commentservice.infrastructure.persistence.repositories;

import org.phong.commentservice.infrastructure.persistence.models.CommentMentionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface CommentMentionRepository extends JpaRepository<CommentMentionEntity, UUID> {
    void deleteAllByMentionUserIdIn(Collection<String> mentionUserIds);

    List<String> findAllByCommentId(UUID commentId);
}