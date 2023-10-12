package com.example.mimimimetr.dto;

import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatGameDto {
    private String name;
    private byte[] avatar;
}
