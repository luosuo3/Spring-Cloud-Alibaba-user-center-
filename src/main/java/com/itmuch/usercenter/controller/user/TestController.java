package com.itmuch.usercenter.controller.user;

import com.itmuch.usercenter.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王峥
 * @date 2020/6/26 6:47 下午
 */
@RestController
public class TestController {
    @GetMapping("/q")
    public User query(User user) {
        return user;
    }
}
