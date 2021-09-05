package bm.services.impl;

import bm.DTO.Category;
import bm.DTO.Post;
import bm.repositories.interfaces.CategoryRepository;
import bm.services.interfaces.CategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        category.validate();
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

    public Category findCategoryByPost(Post post){
        return this.categoryRepository.findCategoryByPost(post);
    }

}
