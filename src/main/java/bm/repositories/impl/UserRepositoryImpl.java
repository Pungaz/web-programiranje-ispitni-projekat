package bm.repositories.impl;

import bm.DTO.User;
import bm.repositories.interfaces.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User findUser(String username) {
        return null;
    }

    @Override
    public List<User> listAllUsers() {
        return null;
    }

    @Override
    public User editUser(User user, String username) {
        return null;
    }

    @Override
    public void changeStatus(String username, int newStatus) {

    }
}
