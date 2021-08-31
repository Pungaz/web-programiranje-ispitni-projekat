package bm.services;

import bm.model.Category;
import bm.repositories.CategoryRepository;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        return this.categoryRepository.addCategory(category);
    }

    public List<Category> listAllCategories(){
        return this.categoryRepository.listAllCategories();
    }


}
