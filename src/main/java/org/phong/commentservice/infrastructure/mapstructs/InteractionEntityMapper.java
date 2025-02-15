package org.phong.commentservice.infrastructure.mapstructs;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.phong.commentservice.dtos.requests.InteractionCreateRequest;
import org.phong.commentservice.dtos.requests.InteractionDeleleRequest;
import org.phong.commentservice.dtos.responds.RelevantInteractionRespond;
import org.phong.commentservice.infrastructure.persistence.models.InteractionEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface InteractionEntityMapper {
    InteractionEntity toEntity(InteractionCreateRequest interactionCreateRequest);

    InteractionCreateRequest toDto(InteractionEntity interactionEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InteractionEntity partialUpdate(InteractionCreateRequest interactionCreateRequest, @MappingTarget InteractionEntity interactionEntity);

    InteractionEntity toEntity(InteractionDeleleRequest interactionDeleleRequest);

    InteractionDeleleRequest toDto1(InteractionEntity interactionEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InteractionEntity partialUpdate(InteractionDeleleRequest interactionDeleleRequest, @MappingTarget InteractionEntity interactionEntity);

    InteractionEntity toEntity(RelevantInteractionRespond relevantInteractionRespond);

    RelevantInteractionRespond toDto2(InteractionEntity interactionEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InteractionEntity partialUpdate(RelevantInteractionRespond relevantInteractionRespond, @MappingTarget InteractionEntity interactionEntity);
}