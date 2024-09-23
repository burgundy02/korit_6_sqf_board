package com.study.SpringSecurityMybatis.service;

import com.study.SpringSecurityMybatis.dto.request.ReqModifyCommentDto;
import com.study.SpringSecurityMybatis.dto.request.ReqWriteCommentDto;
import com.study.SpringSecurityMybatis.dto.response.RespCommentDto;
import com.study.SpringSecurityMybatis.entity.Comment;
import com.study.SpringSecurityMybatis.exception.AccessDeniedException;
import com.study.SpringSecurityMybatis.repository.CommentMapper;
import com.study.SpringSecurityMybatis.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public void write(ReqWriteCommentDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        commentMapper.save(dto.toEntity(principalUser.getId()));
    }

    public RespCommentDto getComments(Long boardId) {
        return RespCommentDto.builder()
                .comments(commentMapper.findAllByBoardId(boardId))
                .commentCount(commentMapper.getCommentCountByBoardId(boardId))
                .build();
    }

    public void deleteComment(Long commentId) {
        accessCheck(commentId);
        commentMapper.deleteById(commentId);
    }

    public void modifyComment(ReqModifyCommentDto dto) {
       accessCheck((dto.getCommentId()));
       commentMapper.updateById(dto.toEntity());
    }

    // 위의 두 코드가 겹치니까 여기서 따로 뺸 후 위에서 호출
    private void accessCheck(Long commentId) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Comment comment = commentMapper.findById(commentId); // comment에 대한 권한 확인
        // 댓글의 작성자가 principalUser의 id와 같은지 확인(여기서 principalUsr null 체크 할 필요 없음)
        if(principalUser.getId() != comment.getWriterId()) {
            throw new AccessDeniedException();
        }
    }

}