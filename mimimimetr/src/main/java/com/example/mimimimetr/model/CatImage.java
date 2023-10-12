package com.example.mimimimetr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "cats_image")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte[] content;

    @OneToOne
    @JoinColumn(name = "cat_id", nullable = false)
    private Cat cat;
}
