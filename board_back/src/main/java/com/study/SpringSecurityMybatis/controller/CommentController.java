package com.study.SpringSecurityMybatis.controller;

import com.study.SpringSecurityMybatis.dto.request.ReqModifyCommentDto;
import com.study.SpringSecurityMybatis.dto.request.ReqWriteCommentDto;
import com.study.SpringSecurityMybatis.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/board/comment")
    public ResponseEntity<?> writeComment(@RequestBody ReqWriteCommentDto dto) {
        commentService.write(dto);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/board/{boardId}/comments")
    public ResponseEntity<?> getComments(@PathVariable Long boardId) {
        return ResponseEntity.ok().body(commentService.getComments(boardId));
    }

    @DeleteMapping("/board/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().body(true);
    }

    // 댓글 수정 / 여기서 패스벨리어블은 크게 의미가 없다 이유는 dto에 id가 들어 있으니까(정보가 다 들어있어서 그냥 dto 받아오면 됨)
                                                // 프론트에서 commentModifyData를 받아옴, commentModifyData에
    @PutMapping("/board/comment/{commentId}")  // 아이디도 포함 돼 있으니까 매개변수로 그냥 Dto로 받아오면 됨
    public ResponseEntity<?> modifyComment(@RequestBody ReqModifyCommentDto dto){
        commentService.modifyComment(dto);
        return ResponseEntity.ok().body(true);
    }

    /**
     * 프론트에서 받은후 controller작성, dto에 데이터 잘 들어오는지 확인 해줘야 함.
     * syso(dto);
     */

}