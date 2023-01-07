package ru.practicum.explore.with.me.categories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explore.with.me.categories.model.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
}