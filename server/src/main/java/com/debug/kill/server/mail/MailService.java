package com.debug.kill.server.mail;

import com.debug.kill.server.dto.MailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * ClassName: MailService
 * Description: 发送邮件
 * Author: Leo
 * Date: 2020/5/14-23:01
 * email 1437665365@qq.com
 */
@Service
@Slf4j
@EnableAsync
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    private static final String MAIL_SEND_FROM = "xu55214881@163.com";

    @Autowired
    private Environment env;


    /**
     * 发送简单邮件
     */
    @Async
    public void sendSimpleEmail(final MailDto dto) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(env.getProperty("mail.send.from"));
            message.setTo(dto.getTos());
            message.setSubject(dto.getSubject());
            message.setText(dto.getContent());

            mailSender.send(message);
            log.info("发送简单文本文件-发送成功");

        } catch (Exception e) {
            log.error("发送简单文本文件-发送异常", e.fillInStackTrace());
        }
    }

    /**
     * 发送花哨邮件
     *
     * @param dto
     */
    @Async
    public void sendHTMLEmail(final MailDto dto) {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = null;
            try {
                //true表示需要创建一个multipart message
                helper = new MimeMessageHelper(message, true, "UTF-8");
                //发送者，必须和application.yml中的username一致，否则会报错
                helper.setFrom(env.getProperty("mail.send.from"));
                //接收者
                helper.setTo(dto.getTos());
                //邮件主题
                helper.setSubject(dto.getSubject());
                //邮件内容
                helper.setText(dto.getContent(), true);

                mailSender.send(message);
                log.info("html邮件发送成功");
            } catch (Exception e) {
                log.error("发送html邮件时发生异常！", e.fillInStackTrace());
                e.printStackTrace();
            }

    }
}
