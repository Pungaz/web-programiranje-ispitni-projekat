package bm.repositories.interfaces;

import bm.DTO.User;

import java.util.List;

public interface UserRepository {
    public User addUser(User user);

    public User findUser(String username);

    public List<User> listAllUsers();

    public User editUser(User user, String username);

    public void changeStatus(String username, int newStatus);
}
