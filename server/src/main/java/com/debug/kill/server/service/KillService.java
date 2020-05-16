package com.debug.kill.server.service;

/**
 * @program: sell
 * @ClassName: KillService
 * @Description: TODO
 * @Author: Leo
 * @Date: 2020/5/14-17:44
 */
public interface KillService {
    //秒杀成功与否
    Boolean killItem(Integer killId,Integer userId) throws Exception;

    Boolean killItemV2(Integer killId, Integer userId) throws Exception;

    Boolean killItemV3(Integer killId, Integer userId) throws Exception;

    Boolean killItemV4(Integer killId, Integer userId) throws Exception;

    Boolean killItemV5(Integer killId, Integer userId) throws Exception;

}
