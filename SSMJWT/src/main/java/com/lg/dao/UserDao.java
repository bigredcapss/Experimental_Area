package com.lg.dao;

import com.lg.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository("userDao")
@Mapper
public interface UserDao {
    User login (User user);
}
