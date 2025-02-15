package org.phong.commentservice.services;

import org.phong.commentservice.dtos.requests.CommentContentUpdateRequest;
import org.phong.commentservice.dtos.requests.CommentCreateRequest;
import org.phong.commentservice.dtos.responds.CommentCreatedRespond;
import org.phong.commentservice.dtos.responds.CommentEntityDto;
import org.phong.commentservice.enums.BusinessErrorEnums;
import org.phong.commentservice.events.producers.CommentContentUpdatedEvent;
import org.phong.commentservice.events.producers.CommentCreatedEvent;
import org.phong.commentservice.events.producers.CommentDeletedEvent;
import org.phong.commentservice.events.producers.CommentsOfPostDeletedEvent;
import org.phong.commentservice.exceptions.CommentNotFoundException;
import org.phong.commentservice.infrastructure.mapstructs.CommentEntityMapper;
import org.phong.commentservice.infrastructure.persistence.models.CommentEntity;
import org.phong.commentservice.infrastructure.persistence.repositories.CommentRepository;
import org.phong.commentservice.infrastructure.rabbitmq.RabbitMQPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentEntityMapper commentEntityMapper;
    private final RabbitMQPublisher rabbitMQPublisher;
    private final ApplicationEventPublisher eventPublisher;

    public CommentService(CommentRepository commentRepository,
                          CommentEntityMapper commentEntityMapper, RabbitMQPublisher rabbitMQPublisher, ApplicationEventPublisher eventPublisher) {
        this.commentRepository = commentRepository;
        this.commentEntityMapper = commentEntityMapper;
        this.rabbitMQPublisher = rabbitMQPublisher;
        this.eventPublisher = eventPublisher;
    }

    public CommentEntityDto getComment(UUID id) {
        CommentEntity commentEntityDto = findById(id);

        return commentEntityMapper.toDto4(commentEntityDto);
    }

    public List<CommentEntityDto> getAllCommentsInPost(UUID postId) {
        List<CommentEntity> commentEntities = commentRepository.findAllByPostId(postId);

        return commentEntities.stream()
                .map(commentEntityMapper::toDto4)
                .toList();
    }

    @Transactional
    public CommentCreatedRespond createComment(CommentCreateRequest commentCreateRequest) {
        CommentEntity commentEntity = commentEntityMapper.toEntity(commentCreateRequest);

        CommentEntity commentCreatedEntity = commentRepository.save(commentEntity);

        eventPublisher.publishEvent(new CommentCreatedEvent(
                commentCreatedEntity.getId(),
                commentCreatedEntity.getPostId(),
                commentCreatedEntity.getContent()
        ));

        return commentEntityMapper.toDto2(commentCreatedEntity);
    }

    public void deleteCommentById(UUID commentId) {
        commentRepository.deleteById(commentId);

        rabbitMQPublisher.sendMessage("internal.comment.deleted", new CommentDeletedEvent(commentId));
    }

    public void deleteCommentsByPostId(UUID postId) {
        List<UUID> commentIdsToDelete = commentRepository.findAllCommentIdByPostId(postId);

        commentRepository.deleteAllByPostId(postId);

        eventPublisher.publishEvent(new CommentsOfPostDeletedEvent(commentIdsToDelete));
    }

    public CommentEntity findById(UUID commentId) {
        return commentRepository.findById(commentId).
                orElseThrow(() -> new CommentNotFoundException(BusinessErrorEnums.COMMENT_NOT_FOUND.getMessage()));
    }

    public CommentEntity getReferenceById(UUID commentId) {
        return commentRepository.getReferenceById(commentId);
    }

    @Transactional
    public void updateCommentContent(UUID commentId, CommentContentUpdateRequest commentContentUpdateRequest) {
        CommentEntity commentEntity = findById(commentId);

        commentEntity.setContent(commentContentUpdateRequest.content());

        eventPublisher.publishEvent(
                new CommentContentUpdatedEvent(
                        commentId,
                        commentContentUpdateRequest.content()
                )
        );

        commentRepository.save(commentEntity);
    }
}
