CREATE TABLE comment_interactions
(
    comment_id       UUID         NOT NULL,
    created_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    interactor_id    UUID         NOT NULL,
    interaction_type VARCHAR(255) NOT NULL,
    CONSTRAINT pk_comment_interactions PRIMARY KEY (comment_id)
);

CREATE TABLE comment_mentions
(
    comment_id      UUID NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    mention_username VARCHAR(255) NOT NULL,
    CONSTRAINT pk_comment_mentions PRIMARY KEY (comment_id)
);

CREATE TABLE comments
(
    id                UUID         NOT NULL,
    created_at        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    parent_comment_id UUID,
    post_id           UUID         NOT NULL,
    commenter_id      UUID         NOT NULL,
    content           VARCHAR(255) NOT NULL,
    CONSTRAINT pk_comments PRIMARY KEY (id)
);

ALTER TABLE comment_interactions
    ADD CONSTRAINT FK_COMMENT_INTERACTIONS_ON_COMMENT FOREIGN KEY (comment_id) REFERENCES comments (id);

ALTER TABLE comment_mentions
    ADD CONSTRAINT FK_COMMENT_MENTIONS_ON_COMMENT FOREIGN KEY (comment_id) REFERENCES comments (id);