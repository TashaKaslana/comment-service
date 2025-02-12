package org.phong.commentservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessErrorEnums {
    COMMENT_NOT_FOUND("Comment not found"),
    COMMENT_CONTENT_EMPTY("Comment content cannot be empty"),
    COMMENT_UPDATE_FAILED("Failed to update comment"),
    COMMENT_DELETE_FAILED("Failed to delete comment"),

    INVALID_POST_ID("Invalid post ID"),
    INVALID_COMMENT_ID("Invalid comment ID"),
    INVALID_USER_ID("Invalid user ID"),

    INVALID_INTERACTION_TYPE("Invalid interaction type"),

    POST_NOT_FOUND("Post not found"),
    USER_NOT_FOUND("User not found"),
    USER_NOT_AUTHORIZED("User not authorized");

    private final String message;
}
