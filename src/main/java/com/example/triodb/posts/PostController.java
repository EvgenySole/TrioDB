package com.example.triodb.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "posts")
public class PostController {
    public final PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Posts> getPosts(){
        return postService.getPosts();
    }

    @GetMapping(path ="{postId}")
    public List<Posts> getPost(@PathVariable("postId") Long eventId){
        return postService.getPost(eventId);
    }
    @PostMapping
    public void registerNewPost(@RequestBody Posts event){
        postService.addNewPost(event);
    }
    @DeleteMapping(path = "{postId}")
    public void deletePost(@PathVariable("postId") Long eventId){
        postService.deletePost(eventId);
    }
    @PutMapping(path = "{postId}")
    public void updatePost(
            @PathVariable("postId") Long postId,
            @RequestParam(required = false) String postName
            )
    {
        postService.updatePost(postId, postName);
    }
}
