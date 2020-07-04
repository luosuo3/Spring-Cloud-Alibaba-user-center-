package com.itmuch.usercenter.controller.user;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.google.common.collect.Maps;
import com.itmuch.usercenter.auth.CheckLogin;
import com.itmuch.usercenter.dto.JwtTokenRespDTO;
import com.itmuch.usercenter.dto.LoginRespDTO;
import com.itmuch.usercenter.dto.UserLoginDTO;
import com.itmuch.usercenter.dto.UserRespDTO;
import com.itmuch.usercenter.model.User;
import com.itmuch.usercenter.service.user.UserService;
import com.itmuch.usercenter.util.JwtOperator;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 王峥
 * @date 2020/6/23 4:31 下午
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Resource
    private JwtOperator jwtOperator;
    @Resource
    private WxMaService wxMaService;
    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    @CheckLogin
    public User findById(@PathVariable int id) {
        log.info("我被请求了");
        return this.userService.findById(id);
    }
    /**
     * 模拟生成token(假的登录)
     */
    @GetMapping("/gen-token")
    public String genToken() {
        Map<String, Object> userInfo = new HashMap<>(3);
        userInfo.put("id", 1);
        userInfo.put("wxNickname", "王峥");
        userInfo.put("role", "admin");
        return this.jwtOperator.generateToken(userInfo);
    }

    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody UserLoginDTO userLoginDTO) throws WxErrorException {
//        微信小程序服务端校验是否已经登陆的结果
        WxMaJscode2SessionResult sessionInfo = this.wxMaService.getUserService()
                .getSessionInfo(userLoginDTO.getCode());
//        微信的openId,用户在微信的唯一标识
        String openId = sessionInfo.getOpenid();
        User user = userService.login(userLoginDTO, openId);
        HashMap<String, Object> userInfo = Maps.newHashMap();
        userInfo.put("id", user.getId());
        userInfo.put("wxNickname", user.getWxNickname());
        userInfo.put("role", user.getRoles());
        String token = jwtOperator.generateToken(userInfo);
        return LoginRespDTO.builder()
                .user(
                        UserRespDTO.builder()
                                .id(user.getWxId())
                                .avatarUrl(user.getAvatarUrl())
                                .bonus(user.getBonus())
                                .wxNickname(user.getWxNickname())
                                .build()
                )
                .token(
                        JwtTokenRespDTO.builder()
                                .expirationTime(jwtOperator.getExpirationTime().getTime())
                                .token(token)
                                .build()
                )
                .build();
    }

}
