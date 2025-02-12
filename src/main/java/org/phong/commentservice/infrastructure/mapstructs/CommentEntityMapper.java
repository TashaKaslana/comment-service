package org.phong.commentservice.infrastructure.mapstructs;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.phong.commentservice.dtos.requests.CommentContentUpdateRequest;
import org.phong.commentservice.dtos.requests.CommentCreateRequest;
import org.phong.commentservice.dtos.responds.CommentCreatedRespond;
import org.phong.commentservice.dtos.responds.CommentEntityDto;
import org.phong.commentservice.dtos.responds.CommentEntityPartialRespond;
import org.phong.commentservice.infrastructure.persistence.models.CommentEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentEntityMapper {
    CommentEntity toEntity(CommentCreateRequest commentCreateRequest);

    CommentCreateRequest toDto(CommentEntity commentEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CommentEntity partialUpdate(CommentCreateRequest commentCreateRequest, @MappingTarget CommentEntity commentEntity);

    CommentEntity toEntity(CommentEntityPartialRespond commentEntityPartialRespond);

    CommentEntityPartialRespond toDto1(CommentEntity commentEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CommentEntity partialUpdate(CommentEntityPartialRespond commentEntityPartialRespond, @MappingTarget CommentEntity commentEntity);

    CommentEntity toEntity(CommentCreatedRespond commentCreatedRespond);

    CommentCreatedRespond toDto2(CommentEntity commentEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CommentEntity partialUpdate(CommentCreatedRespond commentCreatedRespond, @MappingTarget CommentEntity commentEntity);

    CommentEntity toEntity(CommentEntityDto commentEntityDto);

    @AfterMapping
    default void linkInteractions(@MappingTarget CommentEntity commentEntity) {
        if (commentEntity.getInteractions() != null) {
            commentEntity.getInteractions().forEach(interaction -> interaction.setComment(commentEntity));
        }
    }

    @AfterMapping
    default void linkMentions(@MappingTarget CommentEntity commentEntity) {
        if (commentEntity.getMentions() != null) {
            commentEntity.getMentions().forEach(mention -> mention.setComment(commentEntity));
        }
    }

    CommentEntityDto toDto4(CommentEntity commentEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CommentEntity partialUpdate(CommentEntityDto commentEntityDto, @MappingTarget CommentEntity commentEntity);

    CommentEntity toEntity(CommentContentUpdateRequest commentContentUpdateRequest);

    CommentContentUpdateRequest toDto3(CommentEntity commentEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CommentEntity partialUpdate(CommentContentUpdateRequest commentContentUpdateRequest, @MappingTarget CommentEntity commentEntity);
}