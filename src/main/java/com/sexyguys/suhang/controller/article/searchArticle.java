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
    public APIResult getPost(@ModelAttribute ArticleVO params) throws CloneNotSupportedException {
        APIResult searchResult = new APIResult();
        if ((params.getPostId() == null) && (params.getCategory() == null || params.getKeyword() == null)) {
            searchResult.statusCode = 412;
            searchResult.bodyMsg = "ERROR : INVALID POST DATA";
            return searchResult;
        }
        if (params.getPostId() == null) {
            ArrayList<Article> data = articleService.searchArticles(params.getCategory(), params.getKeyword());
            if (data.isEmpty()) {
                searchResult.statusCode = 409;
                searchResult.bodyMsg = "ERROR : ARTICLE NOT EXIST";
                return searchResult;
            }

            ArrayList<Article> result = new ArrayList<>();
            for (Article article: data) {
                Article parameter = (Article)article.clone();
                parameter.setCategory(changeCategoryToKorean(parameter.getCategory()));
                parameter.setDifficulty(changeDifficultyToKorean(parameter.getDifficulty()));

                User owner = userService.findOneMember(parameter.getEmail());
                if (owner != null) parameter.setEmail(String.format("%s[%s]",owner.getName(), owner.getSchool()));
                result.add(parameter);
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

    private String changeDifficultyToKorean(String difficulty) {
        return switch (difficulty) {
            case "VHIGH" -> "매우 어려움";
            case "LHIGH" -> "어려움";
            case "MIDDLE" -> "보통";
            case "LLOW" -> "쉬움";
            case "VLOW" -> "매우 쉬움";
            default -> difficulty;
        };
    }

    private String changeCategoryToKorean(String category) {
        return switch (category) {
            case "math1" -> "수I";
            case "math2" -> "수II";
            case "math3" -> "미적분";
            case "math4" -> "수학(상)";
            case "math5" -> "수학(하)";
            case "math6" -> "중등수학";
            case "math7" -> "초등수학";
            default -> category;
        };
    }
}
