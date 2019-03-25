package com.sononio.bostongene.web.model.repository;

import com.sononio.bostongene.web.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Boolean existsByEmail(String email);
}
