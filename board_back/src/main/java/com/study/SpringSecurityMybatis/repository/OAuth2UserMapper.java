package com.study.SpringSecurityMybatis.repository;

import com.study.SpringSecurityMybatis.entity.OAuth2User;
import com.study.SpringSecurityMybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OAuth2UserMapper {
    int save(OAuth2User oAuth2User);

}