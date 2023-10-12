package com.example.mimimimetr.dto;

import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatDto {
    private Long id;
    private String name;
    private Long rateCat;
}
