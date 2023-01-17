package ru.practicum.ewm_main.comments.dto;

import ru.practicum.ewm_main.comments.model.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CommentMapper {
    public static Comment toComment(CommentDto commentDto) {
        return Comment
                .builder()
                .text(commentDto.getText())
                .createdOn(LocalDateTime.now())
                .build();
    }

    public static CommentDto toCommentDto(Comment comment) {
        return CommentDto
                .builder()
                .id(comment.getId())
                .text(comment.getText())
                .authorId(comment.getUser().getId())
                .eventId(comment.getEvent().getId())
                .createdOn(comment.getCreatedOn().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }
}