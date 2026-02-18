package com.example.Post.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Post.entity.Post;

import jakarta.transaction.Transactional;

public interface PostRepository extends JpaRepository<Post , Long> {
Page <Post> findAllByOrderByCreatedAtDesc(Pageable pageable);

//Atomic Update
@Modifying
@Transactional
@Query("Update Post p SET p.likeCount=p.likeCount + 1 WHERE p.id=:id")
void incrementLikes(@Param("id")Long id);
 @Query(value = """
        SELECT * FROM posts p
        WHERE (
            6371000 * acos(
                cos(radians(:lat)) * cos(radians(p.latitude)) *
                cos(radians(p.longitude) - radians(:lng)) +
                sin(radians(:lat)) * sin(radians(p.latitude))
            )
        ) <= :radius
        ORDER BY p.created_at DESC
    """, nativeQuery = true)

    Page<Post> findNearByPosts(Double lat, Double lng, Double radius, Pageable pageable);

}
