package org.phong.commentservice.services;

import jakarta.validation.constraints.NotNull;
import org.phong.commentservice.infrastructure.persistence.models.CommentEntity;
import org.phong.commentservice.infrastructure.persistence.models.CommentMentionEntity;
import org.phong.commentservice.infrastructure.persistence.repositories.CommentMentionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class CommentMentionService {
    private final Pattern MENTION_PATTERN = Pattern.compile("@[a-zA-Z0-9_]+");
    private final CommentMentionRepository commentMentionRepository;
    private final CommentService commentService;

    public CommentMentionService(CommentMentionRepository commentMentionRepository, CommentService commentService) {
        this.commentMentionRepository = commentMentionRepository;
        this.commentService = commentService;
    }

    private List<String> extractMentions(String commentText) {
        List<String> userMentions = new ArrayList<>();
        String[] words = commentText.split("\\s+");

        for (String word : words) {
            if (MENTION_PATTERN.matcher(word).matches()) {
                userMentions.add(word.substring(1));
            }
        }

        return userMentions;
    }

    public void createMentions(UUID commentId, @NotNull String commentText) {
        CommentEntity comment = commentService.findById(commentId);

        List<String> userMentions = extractMentions(commentText);

        List<CommentMentionEntity> entityList = userMentions.stream()
                .map(mentionUsername -> new CommentMentionEntity(comment, mentionUsername))
                .toList();

        commentMentionRepository.saveAll(entityList);
    }

    public void deleteAllMentionById(List<UUID> commentIds) {
        commentMentionRepository.deleteAllById(commentIds);
    }

    public void updateMentions(UUID commentId, String content) {
        List<String> currentMentions = commentMentionRepository.findMentionUserIdsByCommentId(commentId);
        List<String> newMentions = extractMentions(content);

        List<String> mentionIdsToDelete = currentMentions.stream()
                .filter(mention -> !newMentions.contains(mention))
                .toList();

        List<CommentMentionEntity> commentMentionEntities = newMentions.stream()
                .filter(mention -> !currentMentions.contains(mention))
                .map(mention -> {
                    CommentEntity comment = commentService.getReferenceById(commentId);
                    return new CommentMentionEntity(comment, mention);
                })
                .toList();

        commentMentionRepository.deleteByMentionUserIds(mentionIdsToDelete);
        commentMentionRepository.saveAll(commentMentionEntities);
    }
}
