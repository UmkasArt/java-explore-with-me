package ru.practicum.ewm_main.comments.controller;

import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm_main.comments.dto.CommentDto;
import ru.practicum.ewm_main.comments.service.CommentService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/events/{eventId}/comments")
public class PublicCommentController {
    private final CommentService commentService;

    public PublicCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentDto> getAllCommentsForEvent(@Positive @PathVariable Long eventId,
                                                   @PositiveOrZero @RequestParam (defaultValue = "0") int from,
                                                   @Positive @RequestParam (defaultValue = "10") int size) {
        return commentService.getAllCommentsForEvent(eventId, from, size);
    }
}