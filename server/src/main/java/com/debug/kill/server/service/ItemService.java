package com.debug.kill.server.service;

import com.debug.kill.model.entity.ItemKill;

import java.util.List;

/**
 * @program: sell
 * @ClassName: ItemService
 * @Description: 商品接口
 * @Author: Leo
 * @Date: 2020/5/14-16:23
 */
public interface ItemService {
    //获取待秒杀的商品列表
    List<ItemKill> getKillItems()throws Exception;
    //获取商品详情
    ItemKill getKillDetail(Integer id)throws Exception;

}
