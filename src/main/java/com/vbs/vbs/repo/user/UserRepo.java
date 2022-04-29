package com.vbs.vbs.repo.user;

import com.vbs.vbs.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    public User findUserByEmail(String email);
}
