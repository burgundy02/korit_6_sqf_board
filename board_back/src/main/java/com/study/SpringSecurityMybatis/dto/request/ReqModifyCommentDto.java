package com.study.SpringSecurityMybatis.dto.request;

import com.study.SpringSecurityMybatis.entity.Comment;
import lombok.Data;

@Data
public class ReqModifyCommentDto {
    private Long commentId;
    private String content;
                // 매개변수 받을 때 : dto에 없는 것을 매개 변수로 받아온다.(여기선 받아올 필요 없다)
    public Comment toEntity() {
        return Comment.builder()
                // entity에서 id이름이 id라서 여기서 commentId라고 안쓰고 id라고 썼음
                .id(commentId)
                .content(content)
                .build();
    }

}


