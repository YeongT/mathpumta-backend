package com.sexyguys.suhang.repository;

import com.sexyguys.suhang.domain.Article;
import com.sexyguys.suhang.domain.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class JPAArticleRepository implements ArticleRepository{
    private final EntityManager entityManager;

    public JPAArticleRepository(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public void post(Article article) {
        entityManager.persist(article);
    }

    @Override
    public Article findById(int id) {
        return entityManager.find(Article.class, id);
    }

    @Override
    public ArrayList<Article> findByEmail(String email) {
        return (ArrayList<Article>) entityManager.createQuery("select article from Article article where article.email = :email", Article.class).getResultList();
    }

    @Override
    public ArrayList<Article> search(String keyword) {
        return (ArrayList<Article>) entityManager.createQuery("select article from Article article where article.title like CONCAT(:keyword,'%')", Article.class).getResultList();
    }

    @Override
    public void delete(Article article) {
        entityManager.remove(article);
    }

}
