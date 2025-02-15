package org.phong.commentservice.events.producers;

import org.phong.commentservice.events.PublishableEvent;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record CommentsOfPostDeletedEvent(List<UUID> commentIdList) implements Serializable, PublishableEvent {
}
