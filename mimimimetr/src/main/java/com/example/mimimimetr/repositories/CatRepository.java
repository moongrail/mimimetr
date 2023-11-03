package com.example.mimimimetr.repositories;

import com.example.mimimimetr.model.Cat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    Optional<Cat> findByEmail(String name);

    boolean existsByEmail(String email);

    @Query("SELECT c FROM cats c ORDER BY c.rateCat DESC")
    Page<Cat> findTop10(Pageable pageable);

    @Query(value = "SELECT * " +
            "FROM cats " +
            "WHERE email <> :email " +
            "AND id NOT IN (SELECT voted_cat_id FROM likes WHERE cat_id IN (SELECT id FROM cats WHERE email = :email)) " +
            "ORDER BY RANDOM() " +
            "LIMIT :limit", nativeQuery = true)
    List<Cat> findAllByEmailIsNotAndLikesNotContainingRandom(@Param("email") String email, @Param("limit") int limit);
}
