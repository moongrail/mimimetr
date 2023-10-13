package com.example.mimimimetr.repositories;

import com.example.mimimimetr.model.Cat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    Optional<Cat> findByEmail(String name);

    boolean existsByEmail(String email);

    Page<Cat> findAll(Pageable pageable);

    List<Cat> findAllByIdIsNot(Long catId);
}
