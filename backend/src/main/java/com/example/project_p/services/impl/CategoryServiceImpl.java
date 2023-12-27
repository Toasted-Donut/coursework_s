package com.example.project_p.services.impl;

import com.example.project_p.models.Category;
import com.example.project_p.repo.CategoryRepository;
import com.example.project_p.services.CategoryService;
import com.example.project_p.services.utils.UpdateFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){this.categoryRepository = categoryRepository;}
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category updateById(Long id, Category category) {
        Category oldCategory = findById(id);
        if (oldCategory == null){
            return null;
        }
        UpdateFields.updateField(category,oldCategory);
        return save(oldCategory);
    }

    @Override
    public List<Category> saveAll(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

}
