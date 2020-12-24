package com.sexyguys.suhang.service;

import com.sexyguys.suhang.domain.User;
import com.sexyguys.suhang.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;

import static com.sexyguys.suhang.controller.AuthController.encryptPassword;
import static com.sexyguys.suhang.utility.StringUtility.generateString;

@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //이메일 중복이 아닌경우에만 회원가입이 이루어지도록 설계
    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) return null;
        user.setSalt(generateString((int) (Math.random() % 3 + 10)));
        user.setPassword(encryptPassword(user.getPassword(), user.getSalt()));
        userRepository.save(user);
        return findOneMember(user.getId());
    }

    //모든 유저 조회를 위한 메소드
    public ArrayList<User> loadUsers() {
        return userRepository.findAll();
    }

    //기존의 유저의 이메일과 패스워드가 일치할때 새로운 정보로 덮어쓰기하는 함수, SQL Injection을 방지함.
    public void updateMember(User user, User update) {
        User target = findOneMember(user.getEmail());
        if (target != null) {
            if (!target.getPassword().equals(encryptPassword(user.getPassword(), target.getSalt()))) return;
            target.setEmail(update.getEmail());
            target.setName(update.getName());
            target.setPassword(encryptPassword(update.getPassword(), target.getSalt()));
            target.setSchool(update.getSchool());
            userRepository.save(target);
        }
    }

    //암호가 일치할 때만 계정이 삭제되도록 설계함.
    public void deleteMember(User user) {
        User target = findOneMember(user.getEmail());
        if (target == null) return;
        String encryptedPassword = encryptPassword(user.getPassword(), target.getSalt());
        if (encryptedPassword.equals(target.getPassword())) userRepository.delete(target);
    }

    public User findOneMember(int id) {
        return userRepository.findById(id);
    }

    public User findOneMember(String email) {
        return userRepository.findByEmail(email);
    }
}
