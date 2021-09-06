package bm.repositories.interfaces;

import bm.DTO.User;

import java.util.List;

public interface UserRepository {
    public User addUser(User user);

    public User editUser(User user);

    public User findUserByEmail(String email);

    public List<User> listAllUsers(int offset, int limit);
}
