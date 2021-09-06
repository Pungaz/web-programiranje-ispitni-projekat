package bm.services.impl;

import bm.DTO.User;
import bm.exceptions.ValidationException;
import bm.repositories.interfaces.UserRepository;
import bm.services.interfaces.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        user.validate();
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
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

    @Override
    public String login(String email, String password) {

        String hashedPassword = DigestUtils.sha256Hex(password);

        User user = this.userRepository.findUserByEmail(email);
        if (user == null || !user.getPassword().equals(hashedPassword)) {
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24 * 60 * 60 * 1000); // One day

        //Secret should be stored outside of project
        Algorithm algorithm = Algorithm.HMAC256("secret");

        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(email)
                .withClaim("role", user.getUserType())
                .sign(algorithm);
    }
}
