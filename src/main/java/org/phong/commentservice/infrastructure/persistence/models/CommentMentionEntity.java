package org.phong.commentservice.infrastructure.persistence.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import java.util.UUID;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "comment_mentions")
public class CommentMentionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private CommentEntity comment;

    @Column(name = "mention_username", nullable = false)
    private String mentionUserId;

    public CommentMentionEntity(CommentEntity comment, String mentionUserId) {
        this.comment = comment;
        this.mentionUserId = mentionUserId;
    }
}
