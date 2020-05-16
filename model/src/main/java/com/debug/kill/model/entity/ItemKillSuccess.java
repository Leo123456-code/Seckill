package com.debug.kill.model.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
/**
 * 秒杀成功订单表
 */
public class ItemKillSuccess {

    //秒杀成功生成的订单编号
    private String code;
    //商品id
    private Integer itemId;
    //秒杀id
    private Integer killId;
    //用户id
    private String userId;
    //秒杀结果: -1无效  0成功(未付款)  1已付款  2已取消
    private Byte status;
    //创建时间
    private Date createTime;
    //当前时间  -  下单时间
    private Integer diffTime;

    public Integer getDiffTime() {
        return diffTime;
    }

    public void setDiffTime(Integer diffTime) {
        this.diffTime = diffTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getKillId() {
        return killId;
    }

    public void setKillId(Integer killId) {
        this.killId = killId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}