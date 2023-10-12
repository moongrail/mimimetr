package com.example.mimimimetr.repositories;

import com.example.mimimimetr.model.CatImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CatImageRepository extends JpaRepository<CatImage, Long> {

    @Query("SELECT i FROM cats_image i WHERE i.cat.id = :catId")
    Optional<CatImage> findImage(Long catId);
}
