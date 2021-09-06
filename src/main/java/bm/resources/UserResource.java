package bm.resources;

import bm.DTO.User;
import bm.services.interfaces.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(User user) {
        return this.userService.addUser(user);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User editUser(User user) {
        return this.userService.editUser(user);
    }

    @GET
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    public User findUserByEmail(@QueryParam("email") String email) {
        return this.userService.findUserByEmail(email);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> listAllUsers(@DefaultValue("0") @QueryParam("offset") int offset, @DefaultValue("5") @QueryParam("limit") int limit) {
        return this.userService.listAllUsers(offset, limit);
    }
}
