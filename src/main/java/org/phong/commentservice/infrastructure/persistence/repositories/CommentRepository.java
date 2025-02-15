package org.phong.commentservice.infrastructure.persistence.repositories;

import org.phong.commentservice.infrastructure.persistence.models.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {
    List<CommentEntity> findAllByPostId(UUID postId);

    void deleteAllByPostId(UUID postId);

    @Query("SELECT c.id FROM CommentEntity c WHERE c.postId = :postId ORDER BY c.createdAt DESC")
    List<UUID> findAllCommentIdByPostId(UUID postId);
}
