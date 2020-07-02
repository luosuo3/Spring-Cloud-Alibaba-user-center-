package com.itmuch.usercenter.controller.user;

import com.itmuch.usercenter.model.User;
import com.itmuch.usercenter.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 王峥
 * @date 2020/6/23 4:31 下午
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable int id) {
        log.info("我被请求了");
        return this.userService.findById(id);
    }
}
