package com.sexyguys.suhang.controller.article;

import com.sexyguys.suhang.domain.Article;
import com.sexyguys.suhang.domain.models.APIResult;
import com.sexyguys.suhang.service.ArticleService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class searchArticle {
    private final ArticleService articleService;

    public searchArticle(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(value = "/api/article/search")
    @ResponseBody
    public APIResult getPost(@ModelAttribute String keyword) {
        APIResult searchResult = new APIResult();
        if (keyword.isEmpty()) {
            searchResult.statusCode = 412;
            searchResult.bodyMsg = "ERROR : INVALID POST DATA";
            return searchResult;
        }
        ArrayList<Article> result = articleService.searchArticles(keyword);
        if (result.isEmpty()) {
            searchResult.statusCode = 409;
            searchResult.bodyMsg = "ERROR : ARTICLE NOT EXIST";
        }
        searchResult.statusCode = 200;
        searchResult.bodyMsg = "SUCCESS : ARTICLE SEARCHED";
        searchResult.output = result;
        return searchResult;
    }
}
