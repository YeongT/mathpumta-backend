package com.sexyguys.suhang.repository;

import com.sexyguys.suhang.domain.User;

import java.util.ArrayList;

public interface UserRepository {
    User register(User user);
    User findById(int id);
    User findByEmail(String email);
    ArrayList<User> findAll();
}