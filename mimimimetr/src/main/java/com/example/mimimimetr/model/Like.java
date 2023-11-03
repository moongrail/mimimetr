package com.example.mimimimetr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "likes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cat_id", nullable = false)
    private Cat cat;

    @ManyToOne
    @JoinColumn(name = "voted_cat_id", nullable = false)
    private Cat votedCat;
}