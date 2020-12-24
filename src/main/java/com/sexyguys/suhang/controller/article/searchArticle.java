package com.sexyguys.suhang.controller.article;

import com.sexyguys.suhang.domain.Article;
import com.sexyguys.suhang.domain.User;
import com.sexyguys.suhang.domain.models.APIResult;
import com.sexyguys.suhang.domain.vo.ArticleVO;
import com.sexyguys.suhang.service.ArticleService;
import com.sexyguys.suhang.service.UserService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class searchArticle {
    private final ArticleService articleService;
    private final UserService userService;

    public searchArticle(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @PostMapping(value = "/api/article/search")
    @ResponseBody
    public APIResult getPost(@ModelAttribute ArticleVO params) {
        APIResult searchResult = new APIResult();
        if ((params.getPostId() == null) && (params.getCategory() == null || params.getKeyword() == null)) {
            searchResult.statusCode = 412;
            searchResult.bodyMsg = "ERROR : INVALID POST DATA";
            return searchResult;
        }
        if (params.getPostId() == null) {
            ArrayList<Article> result = articleService.searchArticles(params.getCategory(), params.getKeyword());
            if (result.isEmpty()) {
                searchResult.statusCode = 409;
                searchResult.bodyMsg = "ERROR : ARTICLE NOT EXIST";
                return searchResult;
            }
            for(Article article: result) {
                User owner = userService.findOneMember(article.getEmail());
                if (owner != null) article.setEmail(String.format("%s[%s]",owner.getName(), owner.getSchool()));
            }
            searchResult.statusCode = 200;
            searchResult.bodyMsg = "SUCCESS : ARTICLE SEARCHED";
            searchResult.output = result;
            return searchResult;
        }
        Article result;
        try {
            result = articleService.findArticle(Integer.parseInt(params.getPostId()));
            User target = userService.findOneMember(result.getEmail());
            if (target != null) result.setEmail(target.getName());
        } catch (Exception err) {
            searchResult.statusCode = 500;
            searchResult.bodyMsg = "ERROR : error occurred when search article on db.";
            searchResult.error = err.getMessage();
            return searchResult;
        }
        searchResult.statusCode = 200;
        searchResult.bodyMsg = "SUCCESS : ARTICLE SEARCHED";
        searchResult.output = result;
        return searchResult;
    }
}
