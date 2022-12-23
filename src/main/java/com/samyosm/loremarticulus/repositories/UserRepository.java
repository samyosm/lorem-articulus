package com.samyosm.loremarticulus.repositories;

import com.samyosm.loremarticulus.models.UserItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserItem, String> {
    UserItem findUserItemById(String token);
}
