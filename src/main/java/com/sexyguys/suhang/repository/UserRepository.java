package com.sexyguys.suhang.repository;

import com.sexyguys.suhang.domain.User;

import java.util.ArrayList;

//인터페이스를 오버라이딩하여 구현하게끔 하였다.
public interface UserRepository {
    void save(User user);

    User findById(int id);

    User findByEmail(String email);

    ArrayList<User> findAll();

    void delete(User user);
}