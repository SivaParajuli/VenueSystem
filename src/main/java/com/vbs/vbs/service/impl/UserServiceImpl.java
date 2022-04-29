package com.vbs.vbs.service.impl;

import com.vbs.vbs.entity.user.User;
import com.vbs.vbs.repo.user.UserRepo;
import com.vbs.vbs.service.user.UserService;

public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User create(User user) {
        return userRepo.save(user);
    }
}
