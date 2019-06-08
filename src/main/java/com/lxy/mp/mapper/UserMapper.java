package com.lxy.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.mp.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lxy
 * @create 2019-06-07 22:50
 **/
public interface UserMapper extends BaseMapper<User>{
    @Select("select * from user")
    List<User> findAll();
}
