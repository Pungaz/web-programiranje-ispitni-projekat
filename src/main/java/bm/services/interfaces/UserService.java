package bm.services.interfaces;

import bm.DTO.User;

import java.util.List;

public interface UserService {
    public User addUser(User user);

    public User findUserByEmail(String email);

    public List<User> listAllUsers(int offset, int limit);

    public User editUser(User user);

    public String login(String username, String password);

    public boolean isAuthorized(String token);

}