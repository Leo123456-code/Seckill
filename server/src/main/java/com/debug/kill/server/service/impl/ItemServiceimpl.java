package com.debug.kill.server.service.impl;

import com.debug.kill.model.entity.ItemKill;
import com.debug.kill.model.mapper.ItemKillMapper;
import com.debug.kill.model.mapper.ItemMapper;
import com.debug.kill.server.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: ItemServiceimpl
 * Description: TODO
 * Author: Leo
 * Date: 2020/5/14-16:23
 * email 1437665365@qq.com
 */
@Service
@Slf4j
public class ItemServiceimpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemKillMapper itemKillMapper;

    @Override
    public List<ItemKill> getKillItems() throws Exception {
        /**
         * @Description  //获取待秒杀的商品列表
           @Author Leo
         * @Date 16:26 2020/5/14
         * @Param []
         * @return java.util.List<com.debug.kill.model.entity.ItemKill>
        */
        return itemKillMapper.selectAll();
    }

    @Override
    public ItemKill getKillDetail(Integer id) throws Exception {
        /**
         * @Description 获取秒杀详情
           @Author Leo
         * @Date 17:00 2020/5/14
         * @Param [id]
         * @return com.debug.kill.model.entity.ItemKill
        */
        ItemKill itemKill = itemKillMapper.selectById(id);
        if(itemKill == null){
            throw new Exception("获取秒杀详情--待秒杀商品记录不存在");
        }
        return itemKill;
    }
}
