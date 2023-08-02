package com.hurray.gabae.repository;

import com.hurray.gabae.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, String> {
    Optional<UserEntity> findByUserId(String userId);
}
