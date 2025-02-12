package org.phong.commentservice.services;

import org.phong.commentservice.dtos.requests.InteractionCreateRequest;
import org.phong.commentservice.dtos.requests.InteractionDeleleRequest;
import org.phong.commentservice.infrastructure.mapstructs.InteractionEntityMapper;
import org.phong.commentservice.infrastructure.persistence.models.InteractionEntity;
import org.phong.commentservice.infrastructure.persistence.repositories.InteractionRepository;
import org.springframework.stereotype.Service;

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

    public void createInteraction(InteractionCreateRequest createRequest) {
        commentService.findById(createRequest.commentId());

        InteractionEntity interactionEntity = InteractionEntity.builder()
                .commentId(createRequest.commentId())
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

    public void updateInteraction(InteractionCreateRequest updateRequest) {
        commentService.findById(updateRequest.commentId());

        InteractionEntity interactionEntity = interactionRepository.findInteractionEntityByInteractorIdAndCommentIdAllIgnoreCase(
                updateRequest.interactorId(),
                updateRequest.commentId()
        );

        interactionEntity.setInteractionType(updateRequest.interactionType());

        interactionRepository.save(interactionEntity);
    }
}
