package com.example.triodb.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class PostService {
    public final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public List<Posts> getPosts() {
        return postRepository.findAll();
    }

    public List<Posts> getPost(Long postId) {
        boolean exists = postRepository.existsById(postId);
        if (!exists){
            throw new IllegalStateException("Post with id " + postId + " doesn't exsist");
        }
        return postRepository.findAllById(Collections.singleton(postId));
    }

    public void addNewPost(Posts post){
        postRepository.save(post);
        System.out.println(post);
    }

    public void deletePost(Long postId){
        boolean exists = postRepository.existsById(postId);
        if (!exists){
            throw new IllegalStateException("Post with id " + postId + " doesn't exsist");
        }
        postRepository.deleteById(postId);
        System.out.println(postId);
    }

    @Transactional
    public void updatePost(Long postId, String postName) {
        Posts post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalStateException("Post with id " + postId +
                        "doesn't exists")
        );

        if (postId != null  && !Objects.equals(post.getPostId(), postId)) {
            post.setPostId(postId);
        }

        if (postName != null && !Objects.equals(post.getPostName(), postName)) {
            post.setPostName(postName);
        }
    }
}
