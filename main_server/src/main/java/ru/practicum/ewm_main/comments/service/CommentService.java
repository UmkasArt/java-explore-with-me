package ru.practicum.ewm_main.comments.service;

import ru.practicum.ewm_main.comments.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Long userId, Long eventId);

    CommentDto updateComment(Long commentId, Long userId, CommentDto commentDto);

    CommentDto updateCommentByAdmin(Long commentId,  CommentDto commentDto);

    void deleteComment(Long commentId, Long userId);

    void deleteCommentByAdmin(Long commentId);

    List<CommentDto> getAllCommentsByUser(Long userId, int from, int size);

    List<CommentDto> getAllCommentsForEvent(Long eventId, int from, int size);
}