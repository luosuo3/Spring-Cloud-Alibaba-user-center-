package com.itmuch.usercenter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王峥
 * @date 2020/7/3 9:52 上午
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class LoginRespDTO {
    /**
     * token
     */
    private JwtTokenRespDTO token;
    /**
     * 用户信息
     */
    private UserRespDTO user;
}
