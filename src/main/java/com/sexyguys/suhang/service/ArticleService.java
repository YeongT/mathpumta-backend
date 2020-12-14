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
        articleRepository.post(article);
    }

    public Article findArticle(int id) {
        return articleRepository.findById(id);
    }

    public Article updateArticle(int id, String title, String content, String image, String difficulty) {
        Article target = articleRepository.findById(id);
        target.setTitle(title);
        target.setContent(content);
        target.setImage(image);
        target.setDifficulty(difficulty);
        articleRepository.post(target);
        return articleRepository.findById(target.getId());
    }

    public ArrayList<Article> findArticlesByEmail(String email) {
        return articleRepository.findByEmail(email);
    }

    public ArrayList<Article> searchArticles(String keyword) {
        return articleRepository.search(keyword);
    }
}
