package com.example.mimimimetr.repositories;

import com.example.mimimimetr.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Like, Long> {
}
