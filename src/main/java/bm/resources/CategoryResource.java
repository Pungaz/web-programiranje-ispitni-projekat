package bm.resources;

import bm.services.CategoryService;
import bm.model.Category;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/categories")

public class CategoryResource {

    @Inject
    private CategoryService categoryService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Category addCategory(@Valid Category category) {
        return this.categoryService.addCategory(category);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> listAllCategories() {
        return this.categoryService.listAllCategories();
    }




}







