package com.debug.kill.server.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ClassName: KillDto
 * Description: TODO
 * Author: Leo
 * Date: 2020/5/14-17:36
 * email 1437665365@qq.com
 */
@Data
@ToString
public class KillDto implements Serializable {
    @NotNull(message = "id不能为null")
    private Integer killId;

    private Integer userId;
}
