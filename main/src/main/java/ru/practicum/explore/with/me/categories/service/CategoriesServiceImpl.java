package ru.practicum.explore.with.me.categories.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explore.with.me.categories.dto.CategoriesDto;
import ru.practicum.explore.with.me.categories.dto.CategoriesMapper;
import ru.practicum.explore.with.me.categories.model.Categories;
import ru.practicum.explore.with.me.categories.repository.CategoriesRepository;
import ru.practicum.explore.with.me.exception.ConflictException;
import ru.practicum.explore.with.me.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CategoriesServiceImpl implements CategoriesService {

    private CategoriesRepository categoriesRepository;

    @Override
    @Transactional
    public CategoriesDto add(CategoriesDto categoriesDto) {
        try {
            Categories categories = categoriesRepository.save(CategoriesMapper.toCategories(categoriesDto));
            return CategoriesMapper.toCategoriesDto(categories);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public CategoriesDto edit(CategoriesDto categoriesDto) {
        Categories categories = categoriesRepository.findById(categoriesDto.getId())
                .orElseThrow(() -> new NotFoundException("Категория не найдена"));
        if (categoriesDto.getName() != null && !categoriesDto.getName().isEmpty()) {
            categories.setName(categoriesDto.getName());
        }
        try {
            categoriesRepository.saveAndFlush(categories);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(e.getMessage());
        }
        return CategoriesMapper.toCategoriesDto(categories);
    }

    @Override
    @Transactional
    public void delete(Long catId) {
        categoriesRepository.deleteById(catId);
    }

    @Override
    public CategoriesDto getOne(Long catId) {
        Categories categories = categoriesRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException("Категория не найдена"));
        return CategoriesMapper.toCategoriesDto(categories);
    }

    @Override
    public List<CategoriesDto> getAll(Integer from, Integer size) {
        int page = from / size;
        return categoriesRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")))
                .stream()
                .map(CategoriesMapper::toCategoriesDto)
                .collect(Collectors.toList());
    }
}