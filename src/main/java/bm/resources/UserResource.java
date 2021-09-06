//package bm.resources;
//
//import bm.DTO.User;
//import bm.services.interfaces.UserService;
//
//import javax.inject.Inject;
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import java.util.List;
//
//@Path("/users")
//public class UserResource {
//
//    @Inject
//    private UserService userService;
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public User addUser(User user) {
//        return null;
//    }
//
//    @Path("/1")
//    public User findUser(String username) {
//        return null;
//    }
//
//    @Path("/2")
//    public List<User> allUsers() {
//        return null;
//    }
//
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/3")
//    public User editUser(User user) {
//        return null;
//    }
//
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/4")
//    public void changeStatus(User user) {
//
//    }
//}
