package bm.repositories.interfaces;

import bm.models.User;

import java.util.List;

public interface UserRepository {
    public User addUser(User user);

    public void deleteUser(User user);

    public User changeUserType(User user);

    public List<User> listAllUsers();

    public User findUser(User user);
}
