package com.sononio.bostongene.web.api.users;

import com.sononio.bostongene.web.exceptions.ApiErrorContainer;
import com.sononio.bostongene.web.exceptions.ApiException;
import com.sononio.bostongene.web.model.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.logging.Level;

/**
 * Rest controller for users.
 */
@RestController
@Log
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns user by id.
     * @param id user id.
     * @return user object.
     * @throws ApiException if user does not exist.
     */
    @GetMapping("/{id}")
    public User getUser(@PathVariable @NotNull Long id) throws ApiException {
        return userService.getUser(id);
    }

    /**
     * Creates new user.
     * @param userBodyContainer user data.
     * @return user object.
     * @throws ApiException if user data is invalid
     */
    @PostMapping("")
    public User createUser(@RequestBody @Valid UserBodyContainer userBodyContainer) throws ApiException {
        return userService.createUser(userBodyContainer);
    }

    /**
     * Updates user record.
     * @param id user id.
     * @param userBodyContainer new user data.
     * @return user object.
     * @throws ApiException if user data is invalid or user does not exists.
     */
    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable @NotNull Long id,
            @RequestBody @Valid UserBodyContainer userBodyContainer) throws ApiException {
        return userService.updateUser(id, userBodyContainer);
    }

    /**
     * Removes user record.
     * @param id user id.
     * @return id of removed user.
     * @throws ApiException if user does not exists.
     */
    @DeleteMapping("/{id}")
    public RemoveResponseStatus removeUser(@PathVariable Long id) throws ApiException {
        return userService.removeUser(id);
    }

    /**
     * Handle API exceptions and creates beauty containers.
     * @param ex API exception.
     * @return Container of API exception data.
     */
    @ExceptionHandler({ ApiException.class })
    public ResponseEntity handleException(ApiException ex){
        log.log(Level.INFO, ex.getMessage());
        return ResponseEntity
                .status(ex.getType().getHttpStatus())
                .body(new ApiErrorContainer(ex.getType(), ex.getMessage()));
    }
}
