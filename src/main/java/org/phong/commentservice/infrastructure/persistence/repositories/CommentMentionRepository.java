package org.phong.commentservice.infrastructure.persistence.repositories;

import org.phong.commentservice.infrastructure.persistence.models.CommentMentionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentMentionRepository extends JpaRepository<CommentMentionEntity, UUID> {
}