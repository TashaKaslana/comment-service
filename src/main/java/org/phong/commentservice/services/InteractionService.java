package org.phong.commentservice.services;

import org.phong.commentservice.dtos.requests.InteractionCreateRequest;
import org.phong.commentservice.dtos.requests.InteractionDeleleRequest;
import org.phong.commentservice.dtos.requests.InteractionUpdateRequest;
import org.phong.commentservice.dtos.responds.RelevantInteractionRespond;
import org.phong.commentservice.infrastructure.mapstructs.InteractionEntityMapper;
import org.phong.commentservice.infrastructure.persistence.models.CommentEntity;
import org.phong.commentservice.infrastructure.persistence.models.InteractionEntity;
import org.phong.commentservice.infrastructure.persistence.repositories.InteractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InteractionService {
    private final CommentService commentService;
    private final InteractionRepository interactionRepository;
    private final InteractionEntityMapper interactionEntityMapper;

    public InteractionService(CommentService commentService,
                              InteractionRepository interactionRepository,
                              InteractionEntityMapper interactionEntityMapper) {
        this.commentService = commentService;
        this.interactionRepository = interactionRepository;
        this.interactionEntityMapper = interactionEntityMapper;
    }

    public List<RelevantInteractionRespond> getInteractionOfCommentId(UUID commentId) {
        CommentEntity comment = commentService.findById(commentId);

        List<InteractionEntity> interactions = interactionRepository.findAllByComment(comment);

        return interactions.stream()
                .map(interactionEntityMapper::toDto2)
                .toList();
    }

    public void createInteraction(UUID commentId, InteractionCreateRequest createRequest) {
        CommentEntity comment = commentService.findById(commentId);

        InteractionEntity interactionEntity = InteractionEntity.builder()
                .comment(comment)
                .interactorId(createRequest.interactorId())
                .interactionType(createRequest.interactionType())
                .build();

        interactionRepository.save(interactionEntity);
    }

    public void deleteInteraction(InteractionDeleleRequest interactionDeleleRequest) {
        commentService.findById(interactionDeleleRequest.commentId());

        interactionRepository.deleteByInteractorIdAndCommentIdAllIgnoreCase(
                interactionDeleleRequest.interactorId(),
                interactionDeleleRequest.commentId()
        );
    }

    public void deleteAllInteractionsByCommentId(UUID commentId) {
        interactionRepository.deleteAllByCommentId(commentId);
    }

    public void deleteAllInteractionsByCommentIdList(List<UUID> commentIds) {
        interactionRepository.deleteAllById(commentIds);
    }

    public void updateInteraction(UUID commentId, UUID interactorId, InteractionUpdateRequest updateRequest) {
        commentService.findById(commentId);

        InteractionEntity interactionEntity = interactionRepository.findInteractionEntityByInteractorIdAndCommentIdAllIgnoreCase(
                interactorId,
                commentId
        );

        interactionEntity.setInteractionType(updateRequest.interactionType());

        interactionRepository.save(interactionEntity);
    }
}
