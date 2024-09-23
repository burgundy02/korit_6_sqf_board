package com.study.SpringSecurityMybatis.controller;

import com.study.SpringSecurityMybatis.aspect.annotation.ValidAop;
import com.study.SpringSecurityMybatis.dto.request.ReqBoardListDto;
import com.study.SpringSecurityMybatis.dto.request.ReqSearchBoardDto;
import com.study.SpringSecurityMybatis.dto.request.ReqWriteBoardDto;
import com.study.SpringSecurityMybatis.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @ValidAop
    @PostMapping("/board")
    public ResponseEntity<?> write(@Valid @RequestBody ReqWriteBoardDto dto, BindingResult bindingResult) {
        return ResponseEntity.ok().body(Map.of("boardId", boardService.writeBoard(dto)));
    }

    // 어떤 동작을 하고 그 동작에 대한 데이터를 받을 때 getMapping을 씀(전체조회, 단건 조회, 검색)
    // select
    @GetMapping("/board/search")
    public ResponseEntity<?> getSearchBoards(ReqSearchBoardDto dto) {
        System.out.println(dto);
        return ResponseEntity.ok().body(boardService.getSearchBoard(dto));
    }

    @GetMapping("/board/list")
    public ResponseEntity<?> getBoards(ReqBoardListDto dto) {
        return ResponseEntity.ok().body(boardService.getBoardList(dto));
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<?> getDetail(@PathVariable Long boardId) {
        return ResponseEntity.ok().body(boardService.getBoardDetail(boardId));
    }

    @GetMapping("/board/{boardId}/like")
    public ResponseEntity<?> getLikeInfo(@PathVariable Long boardId) {
        return ResponseEntity.ok().body(boardService.getBoardLike(boardId));
    }

    // 추가(성공 횟수를 리턴)
    // insert into
    @PostMapping("/board/{boardId}/like")
    public ResponseEntity<?> like(@PathVariable Long boardId) {
        boardService.like(boardId);
        return ResponseEntity.ok().body(true);
    }
    // 삭제
    @DeleteMapping("/board/like/{boardLikeId}")
    public ResponseEntity<?> dislike(@PathVariable Long boardLikeId) {
        boardService.dislike(boardLikeId);
        return ResponseEntity.ok().body(true);
    }

}