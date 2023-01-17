package ru.practicum.ewm_main.comments.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
public class CommentDto {
    private Long id;

    @NotEmpty
    private String text;

    private Long authorId;

    private Long eventId;

    private String createdOn;
}