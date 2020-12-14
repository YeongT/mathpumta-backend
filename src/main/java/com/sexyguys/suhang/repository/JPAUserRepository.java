package com.sexyguys.suhang.repository;

import com.sexyguys.suhang.domain.User;
import javax.persistence.EntityManager;
import java.util.ArrayList;

public class JPAUserRepository implements UserRepository {
    private final EntityManager entityManager;

    public JPAUserRepository(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public void register(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        ArrayList<User> users = (ArrayList<User>) entityManager.createQuery("select user from User user where user.email = :email", User.class).getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public ArrayList<User> findAll() {
        return (ArrayList<User>) entityManager.createQuery("select user from User user", User.class).getResultList();
    }
}
