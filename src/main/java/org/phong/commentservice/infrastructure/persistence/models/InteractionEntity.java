package org.phong.commentservice.infrastructure.persistence.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "comment_interactions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InteractionEntity extends BaseEntity {
    @Id
    @Column(name = "comment_id", nullable = false, updatable = false)
    private UUID commentId;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "comment_id", referencedColumnName = "id", nullable = false, updatable = false)
    private CommentEntity comment;

    @Column(name = "interactor_id", nullable = false)
    private UUID interactorId;

    @Column(name = "interaction_type", nullable = false)
    private String interactionType;
}

