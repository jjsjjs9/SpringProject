package com.sparta.memo.dto;

import com.sparta.memo.entity.Border;
import lombok.Getter;

@Getter
public class BorderResponseDto {
    private Long id;
    private String username;
    private String contents;

    public BorderResponseDto(Border border) {
        this.id = border.getId();
        this.username =  border.getUsername();
        this.contents = border.getContents();

    }
}