package com.debug.kill.server.mq;

import com.debug.kill.model.dto.KillSuccessUserInfo;
import com.debug.kill.model.entity.ItemKillSuccess;
import com.debug.kill.model.mapper.ItemKillSuccessMapper;
import com.debug.kill.server.dto.MailDto;
import com.debug.kill.server.mail.MailService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * ClassName: RabbitReceiverService
 * Description: Rabbitmq接收消息服务
 * Author: Leo
 * Date: 2020/5/14-22:02
 * email 1437665365@qq.com
 */
@Slf4j
@Service
public class RabbitReceiverService {

    @Autowired
    private MailService mailService;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    private Environment env;

    @Autowired
    private ItemKillSuccessMapper itemKillSuccessMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;



    /**
     * 秒杀异步邮件通知
     * 单一消费者
     */
    @RabbitListener(queues = {"${mq.kill.item.success.email.queue}"},containerFactory = "singleListenerContainer")
    public void consumeEmailMsg(KillSuccessUserInfo info){
        try {
            log.info("秒杀异步邮件通知-接收消息={}",gson.toJson(info));
            //发送邮件
//            MailDto dto = new MailDto(env.getProperty("mail.kill.item.success.subject"),
//                    "这是测试内容",new String[]{info.getEmail()});
            final String context = String.format(env.getProperty("mail.kill.item.success.content"),info.getItemName(),info.getCode());
            MailDto dto = new MailDto(env.getProperty("mail.kill.item.success.subject"),
                    context,new String[]{info.getEmail()});
//            mailService.sendSimpleEmail(dto);
            mailService.sendHTMLEmail(dto);
        }catch (Exception e){
            log.error("秒杀异步邮件通知-接收消息异常",e.fillInStackTrace());
        }
    }

    /**
     *用户秒杀成功后超时未支付
     * 多个消费者
     * @param info
     */
    @RabbitListener(queues = {"${mq.kill.item.success.kill.dead.real.queue}"},containerFactory = "multiListenerContainer")
    public void consumeExpireOrder(KillSuccessUserInfo info){
        //订单超时取消的逻辑

        try {
            log.info("用户秒杀成功后超时未支付-监听者-接收消息={}",info);
            if(info != null){
                ItemKillSuccess itemKillSuccess = itemKillSuccessMapper.selectByPrimaryKey(info.getCode());
                //判断订单状态
                //如果还未支付,订单失效
                if(itemKillSuccess != null && itemKillSuccess.getStatus().intValue() == 0){
                    //订单状态修改为-1
                    int orderNum = itemKillSuccessMapper.expireOrder(info.getCode());
                }
            }

        }catch (Exception e){

        }

    }




}
