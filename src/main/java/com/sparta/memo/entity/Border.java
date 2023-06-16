package com.sparta.memo.entity;

import com.sparta.memo.dto.BorderRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Border {
    private Long id;
    private String username;
    private String contents;

    public Border(BorderRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }

    public void update(BorderRequestDto requestDto) {

        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}
