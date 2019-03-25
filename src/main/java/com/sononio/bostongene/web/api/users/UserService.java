package com.sononio.bostongene.web.api.users;

import com.sononio.bostongene.web.exceptions.ApiEmailExistException;
import com.sononio.bostongene.web.exceptions.ApiException;
import com.sononio.bostongene.web.exceptions.ApiUserNotFoundException;
import com.sononio.bostongene.web.model.User;
import com.sononio.bostongene.web.model.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;

/**
 * Implements logic of user manipulating.
 */
@Service
@Log
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates new user.
     * @param userBodyContainer user data.
     * @return user object.
     * @throws ApiException if user data is invalid
     */
    public User createUser(UserBodyContainer userBodyContainer) throws ApiException {
        log.log(Level.INFO, String.format("Creating user: %s", userBodyContainer.getEmail()));
        checkUserEmailExists(userBodyContainer.getEmail());

        User user = new User(
                userBodyContainer.getEmail(),
                userBodyContainer.getFirstName(),
                userBodyContainer.getLastName(),
                passwordEncoder.encode(userBodyContainer.getPassword()),
                userBodyContainer.getBirthday());

        user = userRepository.save(user);
        log.log(Level.INFO, String.format("User created: %s, %s", user.getId(), user.getEmail()));

        return user;
    }

    /**
     * Returns user by id.
     * @param id user id.
     * @return user object.
     * @throws ApiException if user does not exist.
     */
    public User getUser(Long id) throws ApiException {
        log.log(Level.INFO, String.format("Getting user: %s", id));
        return findUserOrThrowException(id);
    }

    /**
     * Updates user record.
     * @param id user id.
     * @param userBodyContainer new user data.
     * @return user object.
     * @throws ApiException if user data is invalid or user does not exists.
     */
    public User updateUser(Long id, UserBodyContainer userBodyContainer) throws ApiException {
        log.log(Level.INFO, String.format("Updating user: %s", id));

        User user = findUserOrThrowException(id);

        if (!userBodyContainer.getEmail().equals(user.getEmail()))
            checkUserEmailExists(userBodyContainer.getEmail());

        user.setEmail(userBodyContainer.getEmail());
        user.setFirstName(userBodyContainer.getFirstName());
        user.setLastName(userBodyContainer.getLastName());
        user.setBirthday(userBodyContainer.getBirthday());
        user.setPasswordEnc(passwordEncoder.encode(userBodyContainer.getPassword()));

        user = userRepository.save(user);
        return user;
    }

    /**
     * Removes user record.
     * @param id user id.
     * @return id of removed user.
     * @throws ApiException if user does not exists.
     */
    public RemoveResponseStatus removeUser(Long id) throws ApiException {
        log.log(Level.INFO, String.format("Removing user: %s", id));
        User user = findUserOrThrowException(id);
        userRepository.delete(user);

        return new RemoveResponseStatus(id);
    }

    /**
     * Throws exception if user with email exists.
     * @param email email to check.
     * @throws ApiException if user exists.
     */
    private void checkUserEmailExists(String email) throws ApiException {
        if (userRepository.existsByEmail(email)) {
            throw new ApiEmailExistException(String.format("User with this email exists: %s", email));
        }
    }

    /**
     * Search for user by id.
     * @param id id to search.
     * @return user object.
     * @throws ApiException if user does not exists.
     */
    private User findUserOrThrowException(Long id) throws ApiException {
        Optional<User> userOpt = userRepository.findById(id);
        if (!userOpt.isPresent()) {
            throw new ApiUserNotFoundException(String.format("User with id=%s does not exist", id));
        }
        return userOpt.get();
    }
}
