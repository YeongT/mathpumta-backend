package com.sexyguys.suhang.service;

import com.sexyguys.suhang.domain.User;
import com.sexyguys.suhang.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int register(User user) {
        userRepository.register(user);
        return user.getId();
    }

    public ArrayList<User> loadUsers() {
        return userRepository.findAll();
    }

    public User findOneMember(int id) {
        return userRepository.findById(id);
    }

    public User findOneMember(String email) {
        return userRepository.findByEmail(email);
    }
}
