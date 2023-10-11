package com.example.mimimimetr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cats_avatar")
public class CatAvatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte[] content;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "cat_id", nullable = false)
//    private Cat cat_id;
}
