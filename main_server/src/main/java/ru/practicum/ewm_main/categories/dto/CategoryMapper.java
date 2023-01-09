package ru.practicum.ewm_main.categories.dto;

import ru.practicum.ewm_main.categories.model.Category;

public class CategoryMapper {

    public static Category toCategory(CategoryDto categoryDto) {
        return new Category(
                categoryDto.getId(),
                categoryDto.getName());
    }
    public static CategoryDto toCategoryDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }

    public static Category toCategory(NewCategoryDto categoryDto) {
        return new Category(
                categoryDto.getName()
        );
    }
}