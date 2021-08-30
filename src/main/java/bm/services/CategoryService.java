package bm.services;

import bm.model.Category;
import bm.repositories.CategoryRepository;

import javax.inject.Inject;

public class CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        return this.categoryRepository.addCategory(category);
    }

}
