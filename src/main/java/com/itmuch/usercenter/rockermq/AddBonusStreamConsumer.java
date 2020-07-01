package com.itmuch.usercenter.rockermq;

import com.itmuch.usercenter.mapper.BonusEventLogMapper;
import com.itmuch.usercenter.mapper.UserMapper;
import com.itmuch.usercenter.model.BonusEventLog;
import com.itmuch.usercenter.model.User;
import com.itmuch.usercenter.rockermq.dto.UserAddBonusMsgDTO;
import com.itmuch.usercenter.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 王峥
 * @date 2020/6/30 9:27 下午
 */
@Service
@Slf4j
public class AddBonusStreamConsumer {
    @Resource
    private UserService userService;

    @StreamListener(Sink.INPUT)
    public void receive(UserAddBonusMsgDTO message) {
        this.userService.addBonus(message);
    }


}
