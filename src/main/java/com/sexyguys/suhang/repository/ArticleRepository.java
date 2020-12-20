package com.sexyguys.suhang.repository;

import com.sexyguys.suhang.domain.Article;

import java.util.ArrayList;

public interface ArticleRepository {
    void post(Article article);

    Article findById(int id);

    ArrayList<Article> findByEmail(String email);

    ArrayList<Article> search(String keyword);

    void delete(Article article);
}