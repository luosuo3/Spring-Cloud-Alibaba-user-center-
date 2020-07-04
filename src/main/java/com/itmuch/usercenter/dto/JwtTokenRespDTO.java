package com.itmuch.usercenter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王峥
 * @date 2020/7/2 9:37 下午
 */
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class JwtTokenRespDTO {
    /**
     * token
     */
    private String token;
    /**
     * 过期时间
     */
    private Long expirationTime;
}
