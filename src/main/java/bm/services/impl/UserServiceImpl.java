package bm.services.impl;

import bm.DTO.User;
import bm.exceptions.ValidationException;
import bm.repositories.interfaces.UserRepository;
import bm.services.interfaces.UserService;

import javax.inject.Inject;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        user.validate();
        return this.userRepository.addUser(user);
    }

    @Override
    public User editUser(User user) {
        user.validate();
        if (user.getId() == 0){
            throw new ValidationException("User ID is incorrect");
        }
        return this.userRepository.editUser(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> listAllUsers(int offset, int limit) {
        return this.userRepository.listAllUsers(offset, limit);
    }
}
