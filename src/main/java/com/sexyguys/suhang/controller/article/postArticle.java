package com.sexyguys.suhang.controller.article;

import com.sexyguys.suhang.domain.Article;
import com.sexyguys.suhang.domain.models.APIResult;
import com.sexyguys.suhang.service.ArticleService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class postArticle {
    private final ArticleService articleService;

    public postArticle(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(value = "/api/article/post")
    @ResponseBody
    public APIResult getPost(@ModelAttribute Article params) {
        APIResult postResult = new APIResult();
        if (params.getTitle().isEmpty() || params.getContent().isEmpty() || params.getDifficulty().isEmpty() || params.getEmail().isEmpty()) {
            postResult.statusCode = 412;
            postResult.bodyMsg = "ERROR : INVALID POST DATA";
            return postResult;
        }
        Article target = new Article();
        target.initialize(params.getEmail(), params.getTitle(), params.getContent(), params.getImage(), params.getDifficulty());
        articleService.postArticle(target);

        postResult.statusCode = 200;
        postResult.bodyMsg = "SUCCESS : ARTICLE POSTED";
        return postResult;
    }
}
