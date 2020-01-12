package com.gzy.firstdemo.mapper;

import com.gzy.firstdemo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
//这是一个操作数据库的mapper
public interface UserMapper {

    @Select("SELET * FROM user WHERE user_id = #{useID} ")
    public User getUserByUserId(Long userId);

}
