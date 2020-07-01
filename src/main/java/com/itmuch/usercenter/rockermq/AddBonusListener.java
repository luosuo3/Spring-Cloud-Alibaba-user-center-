package com.itmuch.usercenter.rockermq;

import com.itmuch.usercenter.mapper.BonusEventLogMapper;
import com.itmuch.usercenter.mapper.UserMapper;
import com.itmuch.usercenter.model.BonusEventLog;
import com.itmuch.usercenter.model.User;
import com.itmuch.usercenter.rockermq.dto.UserAddBonusMsgDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 王峥
 * @date 2020/6/29 8:29 下午
 */
@Service
@Slf4j
//@RocketMQMessageListener(consumerGroup = "consumerGroup", topic = "add-bonus")
public class AddBonusListener implements RocketMQListener<UserAddBonusMsgDTO> {
    @Resource
    private UserMapper userMapper;
    @Resource
    private BonusEventLogMapper bonusEventLogMapper;

    @Override
    public void onMessage(UserAddBonusMsgDTO message) {
//        当收到消息的时候执行的业务
//        为用户加积分并且加日志到bonus_event_log表里
        Integer bonus = message.getBonus();
        Integer userId = message.getUserId();
        User user = userMapper.selectByPrimaryKey(userId);
        user.setBonus(user.getBonus()+bonus);
        userMapper.updateByPrimaryKey(user);
        bonusEventLogMapper.insert(BonusEventLog.builder()
                .userId(userId)
                .value(bonus)
                .event("CONTRIBUTE")
                .createTime(new Date())
                .description("投稿加积分")
                .build());
        log.info("积分添加完毕!");

    }


}
