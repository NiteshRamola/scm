package in.niteshramola.scm.services;

import in.niteshramola.scm.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> getUserById(String userId);

    Optional<User> getUserByEmail(String email);

    Optional<User> updateUser(User user);

    void deleteUser(String userId);

    boolean isUserExists(String userId);

    boolean isUserExistByEmail(String email);

    List<User> getAllUsers();
}
