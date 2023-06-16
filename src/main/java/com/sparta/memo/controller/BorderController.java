package com.sparta.memo.controller;

import com.sparta.memo.dto.BorderRequestDto;
import com.sparta.memo.dto.BorderResponseDto;
import com.sparta.memo.entity.Border;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BorderController {

    private final Map<Long, Border> memoList = new HashMap<>();

    @PostMapping("/memos")
    public BorderResponseDto createMemo(@RequestBody BorderRequestDto requestDto) {

        // Request-> entity 로 바꿔줘야함.

        Border border = new Border(requestDto);


        // memo max id 체크

        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1;
        border.setId(maxId);

        //DB 저장.

        memoList.put(border.getId(), border);

        // Entity-> ResponseDto로 변환

        BorderResponseDto borderResponseDto = new BorderResponseDto(border);///

        return borderResponseDto;//
    }

    @GetMapping("/post/{id}")
    public List<BorderResponseDto> getMemos() {
        // Map to List

        List<BorderResponseDto> responseList = memoList.values().stream()
                .map(BorderResponseDto::new).toList();
        return responseList;
    }

    // 게시글 수정하기.
    @PutMapping("/post/{id}")

    public Long updateMemo(@PathVariable Long password, @RequestBody BorderRequestDto requestDto) {
        // 해당 메모가 db에 존재하는지 여부
        if (memoList.containsKey(password)) {
            // 해당 메모를 가져오기.
            Border border = memoList.get(password);

            // memo 수정
            border.update(requestDto);
            return border.getId();
        } else {
            throw new IllegalStateException("선택한 메모는 존재하지 않습니다.");
        }
    }

    // 게시글 지우기.

    @DeleteMapping("/post/{id}")
    public Long deleteMemo(@PathVariable Long password) {
        // 해당 메모가 db에 존재하는지
        if (memoList.containsKey(password)) {
            // 해당 게시글 삭제
            memoList.remove(password);
            return  null;

        } else {
            throw new IllegalStateException("선택한 게시글 존재하지 않습니다.");
        }
    }
}
