package org.launchcode.Allergies.controllers;



import org.launchcode.Allergies.models.Post;
import org.launchcode.Allergies.models.User;
import org.launchcode.Allergies.models.data.PostDao;
import org.launchcode.Allergies.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    // Request path: /post
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("posts", postDao.findAll());
        model.addAttribute("title", "My Posts");

        return "post/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddPostForm(Model model) {
        model.addAttribute("title", "Add Review");
        model.addAttribute(new Post());

        model.addAttribute("users", userDao.findAll());
        return "post/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddPostForm(@ModelAttribute @Valid Post newPost,
                                     Errors errors, @RequestParam int userId, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Post");
            return "post";
        }

        User user = userDao.findById(userId).get();

        newPost.setUser(user);
        postDao.save(newPost);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemovePostForm(Model model) {
        model.addAttribute("posts", postDao.findAll());
        model.addAttribute("title", "Remove Review");
        return "post/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemovePostForm(@RequestParam int[] postIds) {

        for (int postId : postIds) {
            postDao.deleteById(postId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "user/{userId}", method = RequestMethod.GET)
    public String user(Model model, @PathVariable int userId) {
        model.addAttribute("posts", userDao.findById(userId).get().getPosts());
        model.addAttribute("title", "My Reviews");
        return "post/index";
    }

    @RequestMapping(value = "edit/{postId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int postId) {
        model.addAttribute("post", postDao.findById(postId));
        model.addAttribute("users", userDao.findAll());

        return "post/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(@RequestParam int postId, @RequestParam String postName, @RequestParam String postDescription, @RequestParam int userId) {
        Post post = postDao.findById(postId).get();
        User user = userDao.findById(userId).get();

        post.setName(postName);
        post.setDescription(postDescription);
        post.setUser(user);

        postDao.save(post);

        return "redirect:";
    }


    }