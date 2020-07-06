package com.itmuch.usercenter.controller.user;

import com.itmuch.usercenter.dto.UserAddBonusDTO;
import com.itmuch.usercenter.model.User;
import com.itmuch.usercenter.rockermq.dto.UserAddBonusMsgDTO;
import com.itmuch.usercenter.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 王峥
 * @date 2020/7/5 1:59 下午
 */
@RestController
@RequestMapping("/users")
public class BonusController {
    @Resource
    private UserService userService;
    @PutMapping("/add-bonus")
    public User addBonus(@RequestBody UserAddBonusDTO userAddBonusDTO) {
        Integer userId = userAddBonusDTO.getUserId();
        userService.addBonus(
                UserAddBonusMsgDTO.builder()
                        .userId(userId)
                        .bonus(userAddBonusDTO.getBonus())
                        .description("兑换分享")
                        .event("BUY")
                        .build()
        );
        return  this.userService.findById(userId);
    }
}
