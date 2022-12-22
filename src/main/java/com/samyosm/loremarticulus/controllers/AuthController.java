package com.samyosm.loremarticulus.controllers;

import com.samyosm.loremarticulus.models.UserItem;
import com.samyosm.loremarticulus.objects.UserRegistration;
import com.samyosm.loremarticulus.repositories.UserRepo;
import com.samyosm.loremarticulus.utils.UIDManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Value("${tokens.admin}")
    private String adminToken;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/getToken")
    public String Generator(
            @RequestBody UserRegistration hints,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken
    ) {
        authToken = authToken.replace("Bearer ", "");
        if (!authToken.equals(adminToken)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        var uidToken = UIDManager.getRandomUidToken(hints.uid());
        UserItem user = new UserItem(
                hints.uid(),
                List.of(uidToken),
                hints.maxUsage(),
                0
        );
        userRepo.save(user);
        return uidToken;
    }
}
