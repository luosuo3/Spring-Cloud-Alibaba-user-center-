package com.itmuch.usercenter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王峥
 * @date 2020/7/2 9:40 下午
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRespDTO {
    /**
     * id
     */
    private String id;
    /**
     * 头像地址
     */
    private String avatarUrl;
    /**
     * 积分
     */
    private Integer bonus;
    /**
     * 微信名字
     */
    private String wxNickname;
}
