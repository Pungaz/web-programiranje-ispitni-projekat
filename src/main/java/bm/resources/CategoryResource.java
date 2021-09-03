package bm.resources;

import bm.models.Category;
import bm.services.impl.CategoryServiceImpl;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/category")
public class CategoryResource {

    @Inject
    private CategoryServiceImpl categoryServiceImpl;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Category addCategory(Category category) {
        return this.categoryServiceImpl.addCategory(category);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Category updateCategory(Category category) {
        return this.categoryServiceImpl.updateCategory(category);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> listAllCategories(@DefaultValue("0") @QueryParam("offset") int offset, @DefaultValue("5") @QueryParam("limit") int limit) {
        return this.categoryServiceImpl.listAllCategories(offset, limit);
    }

    @DELETE
    @Path("/{categoryId}")
    public void delete(@PathParam("categoryId") long categoryId) {
        this.categoryServiceImpl.deleteCategory(categoryId);
    }
}







