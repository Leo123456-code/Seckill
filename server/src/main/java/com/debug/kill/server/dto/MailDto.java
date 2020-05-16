package com.debug.kill.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ClassName: MailDto
 * Description: TODO
 * Author: Leo
 * Date: 2020/5/14-23:05
 * email 1437665365@qq.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MailDto {
    //邮件主题
    private String subject;
    //内容
    private String content;
    //接收人
    private String[] tos;

}
