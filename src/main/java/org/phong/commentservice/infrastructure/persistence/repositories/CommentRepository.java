package org.phong.commentservice.infrastructure.persistence.repositories;

import org.phong.commentservice.infrastructure.persistence.models.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {
    List<CommentEntity> findAllByPostId(UUID postId);

    void deleteAllByPostId(UUID postId);
}
