package com.sexyguys.suhang.service;

import com.sexyguys.suhang.domain.Article;
import com.sexyguys.suhang.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void postArticle(Article article) {
        articleRepository.save(article);
    }

    public Article findArticle(int id) {
        return articleRepository.findById(id);
    }

    public Article updateArticle(Article update) {
        Article target = articleRepository.findById(update.getId());
        if (target == null) return null;
        target.setTitle(update.getTitle());
        target.setContent(update.getContent());
        target.setImage(update.getImage());
        target.setDifficulty(update.getDifficulty());
        articleRepository.save(target);
        return articleRepository.findById(target.getId());
    }

    public void deleteArticle(Article article) {
        Article target = articleRepository.findById(article.getId());
        if (target == null) return;
        articleRepository.delete(target);
    }

    public ArrayList<Article> findArticlesByEmail(String email) {
        return articleRepository.findByEmail(email);
    }

    public ArrayList<Article> searchArticles(String keyword) {
        return articleRepository.search(keyword);
    }
}
