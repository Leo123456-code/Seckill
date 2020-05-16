package com.debug.kill.model.mapper;

import com.debug.kill.model.entity.ItemKill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemKillMapper {
    //获取待秒杀的商品列表
    List<ItemKill> selectAll();
    //获取秒杀详情
    ItemKill selectById(@Param("id") Integer id);

    int updateKillItem(@Param("killId") Integer killId);



    ItemKill selectByIdV2(@Param("id") Integer id);

    int updateKillItemV2(@Param("killId") Integer killId);
}