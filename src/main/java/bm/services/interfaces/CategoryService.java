package bm.services.interfaces;

import bm.models.Category;
import bm.models.Post;

import java.util.List;

public interface CategoryService {
    public Category addCategory(Category category);

    public Category updateCategory(Category category);

    public List<Category> listAllCategories(int offset, int limit);

    public void deleteCategory(long categoryId);

    public Category findCategoryByPost(Post post);
}
