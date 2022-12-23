package com.samyosm.loremarticulus.services;

import com.samyosm.loremarticulus.exceptions.InvalidUserTokenException;
import com.samyosm.loremarticulus.exceptions.UsageLimitReachedException;
import com.samyosm.loremarticulus.exceptions.UserNotFoundException;
import com.samyosm.loremarticulus.models.UserItem;
import com.samyosm.loremarticulus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserItem getUser(String uidToken) throws UserNotFoundException, InvalidUserTokenException, UsageLimitReachedException {
        var temp = uidToken.split("-");
        if (temp.length != 2) {
            throw new InvalidUserTokenException("Invalid token format");
        }

        var uid = temp[0];
        var token = temp[1];

        if (!userRepository.existsById(uid)) {
            throw new UserNotFoundException();
        }

        var user = userRepository.findUserItemById(uid);

        if(!user.getTokens().contains(token)) {
            throw new InvalidUserTokenException("Token not found in user");
        }

        if (user.getUsage() + 1 >= user.getMaxUsage()) {
            throw new UsageLimitReachedException();
        }

        return user;
    }

    public void increaseUserUsage(int count, String uidToken) throws UsageLimitReachedException, UserNotFoundException, InvalidUserTokenException {
        var user = getUser(uidToken);
        user.setUsage(user.getUsage() + count);
        userRepository.save(user);
    }

    public void increaseUserUsage(String uid) throws UsageLimitReachedException, UserNotFoundException, InvalidUserTokenException {
        increaseUserUsage(1, uid);
    }

}
