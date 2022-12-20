package com.samyosm.loremarticulus.repository;

import com.samyosm.loremarticulus.model.user.UserItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserItem, String> {
    UserItem findUserItemByToken(String token);
}
