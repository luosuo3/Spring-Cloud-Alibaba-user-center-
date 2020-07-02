package com.itmuch.usercenter.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * bonus_event_log
 *
 * @author
 */
@Data
@Builder
public class BonusEventLog implements Serializable {
    /**
     * Id
     */
    private Integer id;

    /**
     * user.id
     */
    private Integer userId;

    /**
     * 积分操作值
     */
    private Integer value;

    /**
     * 发生的事件
     */
    private String event;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 描述
     */
    private String description;

    private static final long serialVersionUID = 1L;
}