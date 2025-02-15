package org.phong.commentservice.infrastructure.persistence.repositories;

import org.phong.commentservice.infrastructure.persistence.models.CommentMentionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface CommentMentionRepository extends JpaRepository<CommentMentionEntity, UUID> {
    @Modifying
    @Query("DELETE FROM CommentMentionEntity cm WHERE cm.id IN :mentionUserIds")
    void deleteByMentionUserIds(@Param("mentionUserIds") Collection<String> mentionUserIds);


    @Query("SELECT cm.mentionUserId FROM CommentMentionEntity cm WHERE cm.comment.id = :commentId")
    List<String> findMentionUserIdsByCommentId(@Param("commentId") UUID commentId);
}