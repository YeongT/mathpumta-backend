package com.sexyguys.suhang.controller.article;

import com.sexyguys.suhang.domain.Article;
import com.sexyguys.suhang.domain.models.APIResult;
import com.sexyguys.suhang.service.ArticleService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class viewArticle {
    private final ArticleService articleService;

    public viewArticle(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(value = "/api/article/view")
    @ResponseBody
    public APIResult getPost(@ModelAttribute String postId) {
        APIResult viewResult = new APIResult();
        if (postId.isEmpty()) {
            viewResult.statusCode = 412;
            viewResult.bodyMsg = "ERROR : INVALID POST DATA";
            return viewResult;
        }

        Article result;
        try {
            result = articleService.findArticle(Integer.parseInt(postId));
        } catch (Exception err) {
            viewResult.statusCode = 500;
            viewResult.bodyMsg = "ERROR : error occurred when search article on db.";
            viewResult.error = err.getMessage();
            return viewResult;
        }
        if (result == null) {
            viewResult.statusCode = 409;
            viewResult.bodyMsg = "ERROR : could not find that article in db.";
            return viewResult;
        }
        viewResult.statusCode = 200;
        viewResult.bodyMsg = "SUCCESS : article found";
        return viewResult;
    }
}
