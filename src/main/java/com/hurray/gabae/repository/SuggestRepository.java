package com.hurray.gabae.repository;

import com.hurray.gabae.entity.SuggestEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SuggestRepository extends CrudRepository<SuggestEntity, String> {
    Optional<SuggestEntity> findByUserId(String userId);
}
