package com.example.blog.blogcommunity.mapper;

import com.example.blog.blogcommunity.model.User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,icon_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{iconUrl})")
    public void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId")String accountId);

    @Update("update user set name =#{name},token=#{token},gmt_create=#{gmtModified},icon_url=#{iconUrl} where account_id = #{accountId}" )
    void update(User user);
}
