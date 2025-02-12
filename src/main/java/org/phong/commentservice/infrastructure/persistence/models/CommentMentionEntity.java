package org.phong.commentservice.infrastructure.persistence.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment_mentions")
@Entity
public class CommentMentionEntity extends BaseEntity {
    @Id
    @Column(name = "comment_id", nullable = false, updatable = false)
    private UUID commentId;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "comment_id", referencedColumnName = "id", nullable = false, updatable = false)
    private CommentEntity comment;

    @Column(name = "mention_username", nullable = false)
    private String mentionUserId;

    public CommentMentionEntity(UUID commentId, String mentionUserId) {
        this.commentId = commentId;
        this.mentionUserId = mentionUserId;
    }
}
