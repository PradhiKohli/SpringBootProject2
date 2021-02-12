package com.tts.techtalentblog.controller;

import com.tts.techtalentblog.model.BlogPost;
import com.tts.techtalentblog.repo.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogPostController {

    // @Autowired allows us to implement whats known as dependency injection


    @Autowired
    private BlogPostRepository blogPostRepository;
    private static List<BlogPost> posts = new ArrayList<>();


    //below is a constructor based dependency injection
//depencency injection allows us to give certain objects the dependencies that it needs

//    public BlogPostController(BlogPostRepository blogPostRepository){
   //     this.blogPostRepository = blogPostRepository;
   // }

    @GetMapping(value="/")
    public String index(BlogPost blogPost, Model model){
    // since we are utilizing thymeleaf
    // our output will be generated in a template
    //returning a reference to said template
    // will allow us to show the that we want
        model.addAttribute("posts ", posts);
    return "blogpost/index";
    }

    private BlogPost blogPost;

    //mapping post requests
    @PostMapping(value="/blogpost")
    public String addNewBlogPost(BlogPost blogPost, Model model) {
        //blogPost is our object we're getting from thymeleaf form
     //   blogPostRepository.save(new BlogPost(blogPost.getTitle(), blogPost.getAuthor(), blogPost.getBlogEntry()));
        blogPostRepository.save(blogPost);
        model.addAttribute("title", blogPost.getTitle());
        model.addAttribute("author", blogPost.getAuthor());
        model.addAttribute("blogEntry", blogPost.getBlogEntry());
        return "blogpost/result";
    }
        @GetMapping(value = "/blogpost/new")
        public String newBlog(BlogPost blogPost) {
   //         model.addAttribute("posts", posts);
            return "blogpost/new";
        }

//    @PostMapping("/")
//    public String addNewBlogPost(BlogPost blogPost, Model model){
//        //blogPost from our parameter is the object we are getting from the
//        // the thymeleaf form, we can simple save it in our repository
////        blogPostRepository.save(new BlogPost(
////                blogPost.getTitle(),
////                blogPost.getAuthor(),
////                blogPost.getBlogEntry()
////        ));
//        blogPostRepository.save(blogPost);
//        model.addAttribute( "title", blogPost.getTitle());
//        model.addAttribute( "author", blogPost.getAuthor());
//        model.addAttribute( "blogEntry", blogPost.getBlogEntry());
//
//        return "blogpost/result";


    @RequestMapping(value = "/blogpost/{id}", method = RequestMethod.DELETE)
    public String deletePostWithId(@PathVariable Long id, BlogPost blogPost) {

        blogPostRepository.deleteById(id);
        return "blogpost/index";

    }


//    @RequestMapping(value="/blogpost/{id}", method= RequestMethod.DELETE)
//    public String deletePostWithId(@PathVariable Long id, BlogPost blogPost){
//        blogPostRepository.deleteById(id);
//        return "blogpost/index";
//    }
}
