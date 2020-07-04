package com.itmuch.usercenter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王峥
 * @date 2020/7/3 10:13 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginDTO {
    private String code;
    private String avatarUrl;
    private String wxNickName;

}
