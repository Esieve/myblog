package com.esieve.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.esieve.article.bean.Article;
import com.esieve.article.service.ArticleService;
import com.esieve.category.bean.Category;
import com.esieve.category.service.CategoryService;
import com.esieve.common.bean.OperationResult;
import com.esieve.link.bean.Link;
import com.esieve.link.service.LinkService;
import com.esieve.user.bean.User;
import com.esieve.user.service.UserService;
import com.esieve.util.ImageUtil;
import com.esieve.util.MD5Util;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * Created by 77239 on 2017/4/4/0004.
 */
@Controller
@RequestMapping("/manage")
public class ManageController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ManageController.class);

    @Reference
    private ArticleService articleService;

    @Reference
    private CategoryService categoryService;

    @Reference
    private LinkService linkService;

    @Reference
    private UserService userService;

    @Value("${article.image.path}")
    private String articleImagePath;

    @Value("${user.image.path}")
    private String userImagePath;

    //对文章的管理
    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public String showArticleList(Model model) {
        List<Article> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        model.addAttribute("mainPage", "article.jsp");
        return "manage/manage";
    }

    @RequestMapping(value = {"/article/write", "/article/modify/{articleId}"}, method = RequestMethod.GET)
    public String editArticle(@PathVariable("articleId") Optional<Integer> articleId, HttpServletRequest request, Model model, RedirectAttributes attributes) {
        if (articleId.isPresent()) {
            OperationResult<Article> result = articleService.getArticleByArticleId(articleId.get());
            if (result.isSuccess()) {
                model.addAttribute("article", result.getData());
            } else {
                attributes.addFlashAttribute("info", result.getInfo());
                return "redirect:/manage/article";
            }
        }

        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);

        String[] images = ImageUtil.getImages(request.getServletContext().getRealPath(articleImagePath));
        model.addAttribute("images", images);

        model.addAttribute("mainPage", "articleEditor.jsp");
        return "manage/manage";
    }

    @RequestMapping(value = {"/article/save", "/article/save/{articleId}"}, method = RequestMethod.POST)
    public String saveArticle(@PathVariable("articleId") Optional<Integer> articleId, HttpServletRequest request, RedirectAttributes attributes) {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String image = request.getParameter("image");

        Article article = new Article(categoryId, title, content, image);

        OperationResult result;
        if (articleId.isPresent()) {
            article.setArticleId(articleId.get());
            result = articleService.updateArticle(article);
        } else {
            article.setAbout(false);
            User user = (User) request.getSession().getAttribute("curUser");
            article.setUserId(user.getUserId());
            result = articleService.insertArticle(article);
        }
        attributes.addFlashAttribute("info", result.getInfo());
        return "redirect:/manage/article";
    }

    @RequestMapping(value = "/article/delete/{articleId}", method = RequestMethod.GET)
    public String deleteArticle(@PathVariable("articleId") Integer articleId, RedirectAttributes attributes) {
        OperationResult result = articleService.deleteArticle(articleId);
        attributes.addFlashAttribute("info", result.getInfo());
        return "redirect:/manage/article";
    }

    //对类别的管理
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String showCategoryList(Model model) {
        List<Category> categories = categoryService.getCategories();
        for (Category category : categories) {
            category.setArticleNum(articleService.countArticleNumByCategoryId(category.getCategoryId()));
        }

        model.addAttribute("categories", categories);
        model.addAttribute("mainPage", "category.jsp");
        return "manage/manage";
    }

    @RequestMapping(value = {"/category/add", "/category/modify/{categoryId}"}, method = RequestMethod.GET)
    public String editCategory(@PathVariable("categoryId") Optional<Integer> categoryId, HttpServletRequest request, Model model, RedirectAttributes attributes) {
        if (categoryId.isPresent()) {
            OperationResult<Category> result = categoryService.getCategoryByCategoryId(categoryId.get());
            if (result.isSuccess()) {
                model.addAttribute("category", result.getData());
            } else {
                attributes.addFlashAttribute("info", result.getInfo());
                return "redirect:/manage/category";
            }
        }
        model.addAttribute("mainPage", "categoryEditor.jsp");
        return "manage/manage";
    }

    @RequestMapping(value = {"/category/save", "/category/save/{categoryId}"}, method = RequestMethod.POST)
    public String saveCategory(@PathVariable("categoryId") Optional<Integer> categoryId, HttpServletRequest request, RedirectAttributes attributes) {
        String categoryName = request.getParameter("categoryName");

        OperationResult result;
        if (categoryId.isPresent()) {
            result = categoryService.updateCategory(new Category(categoryId.get(), categoryName));
        } else {
            result = categoryService.insertCategory(categoryName);
        }
        attributes.addFlashAttribute("info", result.getInfo());
        return "redirect:/manage/category";
    }

    @RequestMapping(value = "/category/delete/{categoryId}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable("categoryId") Integer categoryId, RedirectAttributes attributes) {
        OperationResult result = categoryService.deleteCategory(categoryId);
        attributes.addFlashAttribute("info", result.getInfo());
        return "redirect:/manage/category";
    }

    //对链接的管理
    @RequestMapping(value = "/link", method = RequestMethod.GET)
    public String showLinkList(Model model) {
        List<Link> links = linkService.getLinks();
        model.addAttribute("links", links);
        model.addAttribute("mainPage", "link.jsp");
        return "manage/manage";
    }

    @RequestMapping(value = {"/link/add", "/link/modify/{linkId}"}, method = RequestMethod.GET)
    public String editLink(@PathVariable("linkId") Optional<Integer> linkId, Model model, RedirectAttributes attributes) {
        if (linkId.isPresent()) {
            OperationResult<Link> result = linkService.getLinkByLinkId(linkId.get());
            if (result.isSuccess()) {
                model.addAttribute("link", result.getData());
            } else {
                attributes.addFlashAttribute("info", result.getInfo());
                return "redirect:/manage/link";
            }
        }

        model.addAttribute("mainPage", "linkEditor.jsp");
        return "manage/manage";
    }

    @RequestMapping(value = {"/link/save", "/link/save/{linkId}"}, method = RequestMethod.POST)
    public String saveLink(@PathVariable("linkId") Optional<Integer> linkId, HttpServletRequest request, RedirectAttributes attributes) {
        String linkName = request.getParameter("linkName");
        String url = request.getParameter("url");

        OperationResult result;

        if (linkId.isPresent()) {
            result = linkService.updateLink(new Link(linkId.get(), linkName, url));
        } else {
            result = linkService.insertLink(new Link(linkName, url));
        }
        attributes.addFlashAttribute("info", result.getInfo());
        return "redirect:/manage/link";
    }

    @RequestMapping(value = "/link/delete/{linkId}", method = RequestMethod.GET)
    public String deleteLink(@PathVariable("linkId") Integer linkId, RedirectAttributes attributes) {
        OperationResult result = linkService.deleteLink(linkId);
        attributes.addFlashAttribute("info", result.getInfo());
        return "redirect:/manage/link";
    }

    //对图片的管理
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public String showImages(HttpServletRequest request, Model model) {
        String[] userImages = ImageUtil.getImages(request.getServletContext().getRealPath(userImagePath));
        String[] articleImages = ImageUtil.getImages(request.getServletContext().getRealPath(articleImagePath));

        model.addAttribute("userImages", userImages);
        model.addAttribute("articleImages", articleImages);
        model.addAttribute("mainPage", "image.jsp");
        return "manage/manage";
    }

    private String processUpload(HttpServletRequest request) {
        LOGGER.info("process upload");
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            LOGGER.error("upload images error, parse request error, error {}", e);
            return "上传失败";
        }
        if (items == null) {
            LOGGER.error("upload images error, no items");
            return "上传失败";
        }

        Iterator<FileItem> itr = items.iterator();
        while (itr.hasNext()) {
            FileItem item = itr.next();
            if (item.isFormField()) {
                LOGGER.error("upload images error, is form field");
                continue;
            }

            try {
                String imageName = item.getName();
                if ("userImage".equals(item.getFieldName())) {
                    item.write(new File(request.getServletContext().getRealPath(userImagePath) + imageName));
                } else if ("articleImage".equals(item.getFieldName())) {
                    item.write(new File(request.getServletContext().getRealPath(articleImagePath) + imageName));
                }
            } catch (Exception e) {
                LOGGER.error("upload images error, write error, error {}", e);
            }
        }
        return "上传成功";
    }

    @RequestMapping(value = "/image/upload", method = RequestMethod.POST)
    public String uploadImages(HttpServletRequest request, RedirectAttributes attributes) {
        attributes.addFlashAttribute("info", processUpload(request));
        return "redirect:/manage/image";
    }

    private void deleteFile(String path) {
        File file = new File(path);
        file.delete();
    }

    @RequestMapping(value = "/image/delete", method = RequestMethod.POST)
    public String deleteImage(HttpServletRequest request, RedirectAttributes attributes) {
        String[] userImages = request.getParameterValues("userImages");
        String[] articleImages = request.getParameterValues("articleImages");

        if (userImages != null) {
            for (String userImage :
                    userImages) {
                deleteFile(request.getServletContext().getRealPath(userImagePath) + userImage);
            }
        }

        if (articleImages != null) {
            for (String articleImage :
                    articleImages) {
                deleteFile(request.getServletContext().getRealPath(articleImagePath) + articleImage);
            }
        }

        attributes.addFlashAttribute("info", "删除成功");
        return "redirect:/manage/image";
    }

    //用户的管理 超级用户无法修改个人信息
    //todo
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String showUserList(Model model) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("mainPage", "user.jsp");
        return "manage/manage";
    }

    @RequestMapping(value = {"/user/register", "/user/modify/{userId}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable("userId") Optional<Integer> userId, HttpServletRequest request, Model model, RedirectAttributes attributes) {
        if (userId.isPresent()) {
            OperationResult<User> result = userService.getUserByUserId(userId.get());
            if (result.isSuccess()) {
                model.addAttribute("user", result.getData());
            } else {
                attributes.addFlashAttribute("info", result.getInfo());
                return "redirect:/manage/user";
            }
        }

        String[] images = ImageUtil.getImages(request.getServletContext().getRealPath(userImagePath));
        model.addAttribute("images", images);
        model.addAttribute("mainPage", "userEditor.jsp");
        return "manage/manage";
    }

    @RequestMapping(value = {"/user/save", "/user/save/{userId}"}, method = RequestMethod.POST)
    public String saveUser(@PathVariable("userId") Optional<Integer> userId, HttpServletRequest request, RedirectAttributes attributes) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String username = request.getParameter("username");
        String password = MD5Util.encoderPassword(request.getParameter("password"));
        String image = request.getParameter("image");
        String background = request.getParameter("background");
        String biography = request.getParameter("biography");

        OperationResult result;

        if (userId.isPresent()) {
            result = userService.updateUser(new User(userId.get(), username, password, image, background, biography));
        } else {
            result = userService.insertUser(new User(username, password, image, background, biography));
        }
        attributes.addFlashAttribute("info", result.getInfo());
        return "redirect:/manage/user";
    }

    @RequestMapping(value = "/user/delete/{userId}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("userId") Integer userId, RedirectAttributes attributes) {
        OperationResult result = userService.deleteUser(userId);
        attributes.addFlashAttribute("info", result.getInfo());
        return "redirect:/manage/user";
    }

    //对于关于的管理
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String showAbout(Model model) {
        model.addAttribute("about", articleService.getAbout());
        model.addAttribute("mainPage", "about.jsp");
        return "manage/manage";
    }

    @RequestMapping(value = "/about/modify", method = RequestMethod.GET)
    public String modifyAbout(Model model) {
        model.addAttribute("about", articleService.getAbout());
        model.addAttribute("mainPage", "aboutEditor.jsp");
        return "manage/manage";
    }

    @RequestMapping(value = "/about/save", method = RequestMethod.POST)
    public String saveAbout(HttpServletRequest request, RedirectAttributes attributes) {
        String content = request.getParameter("content");
        OperationResult result = articleService.updateAbout(content);
        attributes.addFlashAttribute("info", result.getInfo());
        return "redirect:/manage/about";
    }

    // 退出
    @RequestMapping(value = "/quit", method = RequestMethod.GET)
    public String quit(HttpSession session) {
        session.invalidate();
        return "redirect:/blog";
    }
}
