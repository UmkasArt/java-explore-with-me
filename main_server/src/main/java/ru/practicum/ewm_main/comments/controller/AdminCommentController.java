package ru.practicum.ewm_main.comments.controller;

import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm_main.comments.dto.CommentDto;
import ru.practicum.ewm_main.comments.service.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/admin/comments")
public class AdminCommentController {
    private final CommentService commentService;

    public AdminCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PatchMapping("/{commentId}")
    public CommentDto updateComment(@Positive @PathVariable Long commentId,
                                    @Valid @RequestBody CommentDto commentDto) {
        return commentService.updateCommentByAdmin(commentId, commentDto);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@Positive @PathVariable Long commentId) {
        commentService.deleteCommentByAdmin(commentId);
    }
}