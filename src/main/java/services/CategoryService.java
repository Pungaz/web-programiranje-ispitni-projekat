package services;

import model.Category;
import repositories.CategoryRepository;

import javax.inject.Inject;

public class CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        return this.categoryRepository.addCategory(category);
    }

}
