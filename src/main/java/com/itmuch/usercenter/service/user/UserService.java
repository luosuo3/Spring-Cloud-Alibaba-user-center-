package com.itmuch.usercenter.service.user;

import com.itmuch.usercenter.dto.UserLoginDTO;
import com.itmuch.usercenter.mapper.BonusEventLogMapper;
import com.itmuch.usercenter.mapper.UserMapper;
import com.itmuch.usercenter.model.BonusEventLog;
import com.itmuch.usercenter.model.User;
import com.itmuch.usercenter.model.UserExample;
import com.itmuch.usercenter.rockermq.dto.UserAddBonusMsgDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 王峥
 * @date 2020/6/23 4:29 下午
 */
@Service
@Slf4j
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private BonusEventLogMapper bonusEventLogMapper;

    public User findById(int id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public void addBonus(UserAddBonusMsgDTO message) {
//        当收到消息的时候执行的业务
//        为用户加积分并且加日志到bonus_event_log表里
        Integer bonus = message.getBonus();
        Integer userId = message.getUserId();
        User user = userMapper.selectByPrimaryKey(userId);
        user.setBonus(user.getBonus() + bonus);
        userMapper.updateByPrimaryKey(user);
        bonusEventLogMapper.insert(BonusEventLog.builder()
                .userId(userId)
                .value(bonus)
                .event(message.getEvent())
                .createTime(new Date())
                .description(message.getDescription())
                .build());
        log.info("积分添加完毕!");

    }
    public User login(UserLoginDTO userLoginDTO,String openId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andWxIdEqualTo(openId);
        List<User> users = this.userMapper.selectByExample(userExample);
        if (users.size()== 0) {
            User user = User.builder()
                    .wxId(openId)
                    .bonus(300)
                    .wxNickname(userLoginDTO.getWxNickName())
                    .avatarUrl(userLoginDTO.getAvatarUrl())
                    .roles("user")
                    .createTime(new Date())
                    .updateTime(new Date())
                    .build();
            this.userMapper.insertSelective(user);
            return user;
        }
        return users.get(0);
    }

}
