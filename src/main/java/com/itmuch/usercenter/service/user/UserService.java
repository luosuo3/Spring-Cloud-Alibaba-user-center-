package com.itmuch.usercenter.service.user;

import com.itmuch.usercenter.mapper.UserMapper;
import com.itmuch.usercenter.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 王峥
 * @date 2020/6/23 4:29 下午
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    public  User findById( int id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }
}
