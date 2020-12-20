package com.sexyguys.suhang.repository;

import com.sexyguys.suhang.domain.User;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class JPAUserRepository implements UserRepository {
    private final EntityManager entityManager;

    public JPAUserRepository(EntityManager em) {
        this.entityManager = em;
    }

    //유저 객체를 데이터베이스에 저장하는 함수.
    @Override
    @Modifying
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        ArrayList<User> users = (ArrayList<User>) entityManager.createQuery("select user from User as user where user.email = :email", User.class).setParameter("email", email).getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public ArrayList<User> findAll() {
        return (ArrayList<User>) entityManager.createQuery("select user from User user", User.class).getResultList();
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }
}
