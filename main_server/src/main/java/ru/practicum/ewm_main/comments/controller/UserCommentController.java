package ru.practicum.ewm_main.comments.controller;

import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm_main.comments.dto.CommentDto;
import ru.practicum.ewm_main.comments.service.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/comments")
public class UserCommentController {
    private final CommentService commentService;

    public UserCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{eventId}")
    public CommentDto createComment(@Valid @RequestBody CommentDto commentDto,
                                    @Positive @PathVariable Long userId,
                                    @Positive @PathVariable Long eventId) {
        return commentService.createComment(commentDto, userId, eventId);
    }

    @PatchMapping("/{commentId}")
    public CommentDto updateComment(@Positive @PathVariable Long commentId,
                                    @Positive @PathVariable Long userId,
                                    @Valid @RequestBody CommentDto commentDto) {
        return commentService.updateComment(commentId, userId, commentDto);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@Positive @PathVariable Long userId,
                              @Positive @PathVariable Long commentId) {
        commentService.deleteComment(commentId, userId);
    }

    @GetMapping
    public List<CommentDto> getAllCommentsByUser(@Positive @PathVariable Long userId,
                                                 @PositiveOrZero @RequestParam (defaultValue = "0") int from,
                                                 @Positive @RequestParam (defaultValue = "10") int size) {
        return commentService.getAllCommentsByUser(userId, from, size);
    }
}