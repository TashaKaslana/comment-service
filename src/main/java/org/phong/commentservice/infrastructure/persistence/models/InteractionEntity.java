package org.phong.commentservice.infrastructure.persistence.models;

import jakarta.persistence.*;

import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private CommentEntity comment;

    @Column(name = "interactor_id", nullable = false)
    private UUID interactorId;

    @Column(name = "interaction_type", nullable = false)
    private String interactionType;
}
