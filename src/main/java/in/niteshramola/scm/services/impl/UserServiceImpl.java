package in.niteshramola.scm.services.impl;

import in.niteshramola.scm.entities.User;
import in.niteshramola.scm.helpers.ResourceNotFoundException;
import in.niteshramola.scm.repositories.UserRepository;
import in.niteshramola.scm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

//        user.setPassword(password);

        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user1 = userRepository.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPhone(user.getPhone());
        user1.setAbout(user.getAbout());
        user1.setProfilePic(user.getProfilePic());
        user1.setEnabled(user.isEnabled());
        user1.setEmailVerified(user.isEmailVerified());
        user1.setPhoneVerified(user.isPhoneVerified());
        user1.setProvider(user.getProvider());
        user1.setProviderId(user.getProviderId());

        User user2 = userRepository.save(user1);

        return Optional.of(user2);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public boolean isUserExists(String userId) {
        if (userRepository.existsById(userId)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);

        return user != null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
