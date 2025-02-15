package org.phong.commentservice.controllers;

import jakarta.validation.Valid;
import org.phong.commentservice.dtos.requests.CommentContentUpdateRequest;
import org.phong.commentservice.dtos.requests.CommentCreateRequest;
import org.phong.commentservice.dtos.responds.CommentCreatedRespond;
import org.phong.commentservice.dtos.responds.CommentEntityDto;
import org.phong.commentservice.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentEntityDto> getComment(@PathVariable UUID id) {
        CommentEntityDto comment = commentService.getComment(id);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<List<CommentEntityDto>> getAllCommentsInPost(@PathVariable UUID postId) {
        List<CommentEntityDto> comments = commentService.getAllCommentsInPost(postId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<CommentCreatedRespond> createComment(@Valid @RequestBody CommentCreateRequest commentCreateRequest) {
        CommentCreatedRespond createdComment = commentService.createComment(commentCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable UUID id) {
        commentService.deleteCommentById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deleteCommentsInPost(@PathVariable UUID postId) {
        commentService.deleteCommentsByPostId(postId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/content")
    public ResponseEntity<Void> updateCommentContent(@PathVariable UUID id, @Valid @RequestBody CommentContentUpdateRequest commentContentUpdateRequest) {
        commentService.updateCommentContent(id, commentContentUpdateRequest);
        return ResponseEntity.noContent().build();
    }
}