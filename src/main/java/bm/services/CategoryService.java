package bm.services;

import bm.models.Category;
import bm.repositories.CategoryRepository;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        return this.categoryRepository.addCategory(category);
    }

    public Category updateCategory(Category category) {
        return this.categoryRepository.updateCategory(category);
    }

    public List<Category> listAllCategories(int offset, int limit) {
        return this.categoryRepository.listAllCategories(offset, limit);
    }

    public void deleteCategory(long categoryId) {
        this.categoryRepository.deleteCategory(categoryId);
    }

}
