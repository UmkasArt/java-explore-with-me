package ru.practicum.ewm_main.categories.controller;

import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm_main.categories.dto.CategoryDto;
import ru.practicum.ewm_main.categories.dto.NewCategoryDto;
import ru.practicum.ewm_main.categories.service.CategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/categories")
public class AdminCategoryController {
    private final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PatchMapping
    public CategoryDto updateCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategory(categoryDto);
    }

    @PostMapping
    public CategoryDto createCategory(@Valid @RequestBody NewCategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}