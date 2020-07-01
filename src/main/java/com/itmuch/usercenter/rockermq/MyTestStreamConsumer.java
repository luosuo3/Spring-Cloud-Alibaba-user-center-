package com.itmuch.usercenter.rockermq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.stereotype.Service;

/**
 * @author 王峥
 * @date 2020/6/30 10:47 下午
 */
@Slf4j
@Service
public class MyTestStreamConsumer {
//    全局异常处理
    @StreamListener("errorChannel")
    public void error (Message<?> message) {
        ErrorMessage errorMessage = (ErrorMessage) message;
        log.warn("消息发生异常,errorMessage={}",errorMessage);
    }
}
