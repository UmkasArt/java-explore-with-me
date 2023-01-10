package ru.practicum.ewm_main.categories.dto;

import ru.practicum.ewm_main.categories.model.Category;

public class CategoryMapper {

    public static Category toCategory(CategoryDto categoryDto) {
        return Category
                .builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }

    public static CategoryDto toCategoryDto(Category category) {
        return CategoryDto
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Category toCategory(NewCategoryDto categoryDto) {
        return Category
                .builder()
                .name(categoryDto.getName())
                .build();
    }
}