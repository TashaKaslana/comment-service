package org.phong.commentservice.controllers;

import jakarta.validation.Valid;
import org.phong.commentservice.dtos.requests.InteractionCreateRequest;
import org.phong.commentservice.dtos.requests.InteractionDeleleRequest;
import org.phong.commentservice.dtos.requests.InteractionUpdateRequest;
import org.phong.commentservice.dtos.responds.RelevantInteractionRespond;
import org.phong.commentservice.services.InteractionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/api/comments")
public class InteractionController {
    private final InteractionService interactionService;

    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }


    @GetMapping("/{commentId}/interactions")
    public ResponseEntity<List<RelevantInteractionRespond>> getInteractionsForComment(@PathVariable UUID commentId) {
        List<RelevantInteractionRespond> interactions = interactionService.getInteractionOfCommentId(commentId);

        return ResponseEntity.ok().body(interactions);
    }

    @PostMapping("/{commentId}/interactions")
    public ResponseEntity<Void> createInteractionForComment(@PathVariable UUID commentId, @Valid @RequestBody InteractionCreateRequest interactionCreateRequest) {
        interactionService.createInteraction(commentId, interactionCreateRequest);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{commentId}/interactions/{interactionId}")
        public ResponseEntity<Void> updateInteraction(@PathVariable UUID commentId, @PathVariable UUID interactionId, @Valid @RequestBody InteractionUpdateRequest request) {
        interactionService.updateInteraction(commentId, interactionId, request);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{commentId}/interactions/{interactionId}")
    public ResponseEntity<String> deleteInteraction(@PathVariable UUID commentId, @Valid @PathVariable UUID interactionId) {
    interactionService.deleteInteraction(new InteractionDeleleRequest(commentId, interactionId));

        return ResponseEntity.ok("Deleted interaction with ID: " + interactionId);
    }
}
