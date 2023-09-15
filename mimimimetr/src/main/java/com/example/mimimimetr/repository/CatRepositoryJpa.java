package com.example.mimimimetr.repository;

import com.example.mimimimetr.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatRepositoryJpa extends JpaRepository<Cat, Long> {
    Optional<Cat> findByName(String name);
}
