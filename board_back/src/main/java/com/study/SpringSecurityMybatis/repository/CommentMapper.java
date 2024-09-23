package com.study.SpringSecurityMybatis.repository;

import com.study.SpringSecurityMybatis.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int createTrigger();
    int save(Comment comment);
    // order by 썼으면 무조건 list로 가져와야 함
    List<Comment> findAllByBoardId(Long boardId);
    int getCommentCountByBoardId(Long boardId);
    int deleteById(Long id);
    Comment findById(Long id);
    Comment findByParentId(Long parentId);
    // 매개변수로 id,content 넘어가줘야함(entity가 다 포함하고 있어서 entity로 함)
    int updateById(Comment comment);
}
