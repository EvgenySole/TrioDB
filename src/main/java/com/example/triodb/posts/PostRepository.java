package com.example.triodb.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT s FROM Posts s WHERE s.postId = ?1")
    Optional<Posts> findPostsById(String id);
}