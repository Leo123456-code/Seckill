package com.debug.kill.server.scheduler;

import com.debug.kill.model.entity.ItemKillSuccess;
import com.debug.kill.model.mapper.ItemKillSuccessMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

/**
 * ClassName: SchedulerService
 * Description: 定时任务
 * Author: Leo
 * Date: 2020/5/15-17:33
 * email 1437665365@qq.com
 */
@Service
@Slf4j
public class SchedulerService {

    @Autowired
    private ItemKillSuccessMapper itemKillSuccessMapper;

    /**
     * 定时获取status = 0的订单并判断是否超过TTL,然后进行失效
     *
     */
    //1分钟
    @Scheduled(cron = "0/59 * * * * ?  ")
    public void schedulerExpireOrders(){
        try {
            //批量获取待处理的已保存订单记录
            List<ItemKillSuccess> orders = itemKillSuccessMapper.selectExpireOrders();
            if(orders != null && !orders.isEmpty()){
                orders.stream().forEach(new Consumer<ItemKillSuccess>() {
                    @Override
                    public void accept(ItemKillSuccess i) {
                        //如果下单时间 > 30分钟,将订单状态改为-1
                        if (i != null && i.getDiffTime() > 30){
                            itemKillSuccessMapper.expireOrder(i.getCode());
                        }
                    }
                });
            }
//            for (ItemKillSuccess order : orders) {
//                log.info("当前秒杀成功的订单={}",order);
//            }
        }catch (Exception e){
            log.error("定时获取status = 0的订单并判断是否超过TTL,然后进行失效--发生异常={}",e.fillInStackTrace());
        }
    }

}
