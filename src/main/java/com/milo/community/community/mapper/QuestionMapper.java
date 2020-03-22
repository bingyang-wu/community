package com.milo.community.community.mapper;

import com.milo.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author milo
 * @date 2020/3/22
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
}
