package com.esieve.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.esieve.article.bean.Article;
import com.esieve.article.bean.Page;
import com.esieve.article.service.ArticleService;
import com.esieve.category.bean.Category;
import com.esieve.category.service.CategoryService;
import com.esieve.common.bean.OperationResult;
import com.esieve.link.bean.Link;
import com.esieve.link.service.LinkService;
import com.esieve.user.bean.User;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by 张秦遥 on 2017/4/6/0006.
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    private final static Logger LOGGER = LoggerFactory.getLogger(BlogController.class);

    @Reference
    private ArticleService articleService;

    @Reference
    private CategoryService categoryService;

    @Reference
    private LinkService linkService;

    @Value("${page.size}")
    private int pageSize;

    @RequestMapping(method = RequestMethod.GET)
    public String showBlogView(HttpServletRequest request, Model model, @RequestParam(value = "page", required = false) String page,
                               @RequestParam(value = "search", required = false) String search) {

        List<Article> recentArticles = articleService.getRecentArticles();
        List<Article> mostViewedArticles = articleService.getMostViewedArticles();
        List<Link> links = linkService.getLinks();
        request.getServletContext().setAttribute("recentArticles", recentArticles);
        request.getServletContext().setAttribute("mostViewedArticles", mostViewedArticles);
        request.getServletContext().setAttribute("links", links);

        //首页用户头像,未登录默认显示ted
        //todo 未登录显示特殊头像
        User user = (User) request.getSession().getAttribute("curUser");
        request.getServletContext().setAttribute("userImage", "/images/user/" + (user == null ? "ted.jpg" : user.getImage()));

        if (page == null || page == "") {
            page = "1";
        }
        Page pageUtil = new Page(Integer.parseInt(page), 4);
        List<Article> articles = null;
        String pageCode = null;
        if (search != null && !search.equals("")) {
            articles = articleService.searchArticles(search);
            pageCode = this.genPagination(articles.size(), Integer.parseInt(page), "&search=" + search);
        } else {
            articles = articleService.pagination(pageUtil);
            int total = articleService.countArticleNum();
            pageCode = this.genPagination(total, Integer.parseInt(page), "");
        }

        model.addAttribute("pageCode", pageCode);
        model.addAttribute("articles", articles);
        model.addAttribute("mainPage", "article.jsp");
        return "blog/blog";
    }

    private String genPagination(int totalNum, int curPage, String search) {
        int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        StringBuffer pageCode = new StringBuffer();
        pageCode.append("<ul class=\"pagination\">");
        pageCode.append("<li class='waves-effect'><a href='blog?page=1'" + search + "><i class=\"material-icons\">first_page</i></a></li>");
        if (curPage == 1) {
            pageCode.append("<li class=\"disabled\"><a><i class=\"material-icons\">chevron_left</i></a></li>");
        } else {
            pageCode.append("<li class='waves-effect'><a href=\"blog?page=" + (curPage - 1) + search + "\"><i class=\"material-icons\">chevron_left</i></a></li>");
        }
        for (int i = curPage - 2; i <= curPage + 2; i++) {
            if (i < 1 || i > totalPage) {
                continue;
            }
            if (i == curPage) {
                pageCode.append("<li class='active waves-effect'><a href='#'>" + i
                        + "</a></li>");
            } else {
                pageCode.append("<li class='waves-effect'><a href='blog?page=" + i + "'>" + i
                        + "</a></li>");
            }
        }
        if (curPage == totalPage) {
            pageCode.append("<li class='disabled'><a><i class=\"material-icons\">chevron_right</i></a></li>");
        } else {
            pageCode.append("<li class='waves-effect'><a href='blog?page=" + (curPage + 1) + search
                    + "'><i class=\"material-icons\">chevron_right</i></a></li>");
        }
        pageCode.append("<li class='waves-effect'><a href='blog?page=" + totalPage + search + "'><i class=\"material-icons\">last_page</i></a></li>");
        pageCode.append("</ul>");
        return pageCode.toString();
    }

    //文章详情页的展示
    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
    public String showArticleView(@PathVariable("articleId") int articleId, Model model, RedirectAttributes attributes) {
        OperationResult<Article> result = articleService.getArticleByArticleId(articleId);

        Preconditions.checkNotNull(result);

        if (result.isSuccess()) {
            OperationResult addClickResult = articleService.addClicks(articleId);
            if (addClickResult.isSuccess() == false) {
                model.addAttribute("info", result.getInfo());
//                LOGGER.error("add click error.\tarticle:%s", articleId);
            }
            Article preArticle = articleService.getPreArticle(articleId);
            Article nextArticle = articleService.getNextArticle(articleId);
            model.addAttribute("articleId", articleId);
            model.addAttribute("article", result.getData());
            model.addAttribute("preArticle", preArticle);
            model.addAttribute("nextArticle", nextArticle);
            model.addAttribute("mainPage", "articleDetail.jsp");
            return "blog/blog";
        } else {
            attributes.addFlashAttribute("info", result.getInfo());
            return "redirect:/blog";
        }
    }

    //类别列表的展示
    @RequestMapping(value = {"/category", "/category/{categoryId}"}, method = RequestMethod.GET)
    public String showCategoryView(@PathVariable("categoryId") Optional<Integer> categoryId, Model model) {
        Map<Integer, List<Article>> articlesList = new HashMap<>();

        List<Category> categories = categoryService.getCategories();
        for (Category category : categories) {
            List<Article> articles = articleService.getArticlesByCategoryId(category.getCategoryId());
            articlesList.put(category.getCategoryId(), articles);
        }

        if (categoryId.isPresent()) {
            model.addAttribute("categoryId", categoryId.get());
        } else {
            model.addAttribute("categoryId", "");
        }

        model.addAttribute("categories", categories);
        model.addAttribute("articlesList", articlesList);
        model.addAttribute("mainPage", "category.jsp");
        return "blog/blog";
    }

    //归档列表展示 todo 按月份归档，增加分页
    @RequestMapping(value = "/archive", method = RequestMethod.GET)
    public String showArchiveView(Model model) {
        List<Article> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        model.addAttribute("mainPage", "archive.jsp");
        return "blog/blog";
    }

    //消息页面的展示
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String showMessageView(Model model) {
//        model.addAttribute("articleId", 0); todo remove?
        model.addAttribute("mainPage", "message.jsp");
        return "blog/blog";
    }

    //关于页面的展示
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String showAboutView(Model model) {
        Article about = articleService.getAbout();
        model.addAttribute("about", about);
        model.addAttribute("mainPage", "about.jsp");
        return "blog/blog";
    }
}
