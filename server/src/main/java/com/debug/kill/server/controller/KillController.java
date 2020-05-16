package com.debug.kill.server.controller;

import com.debug.kill.api.enums.StatusCode;
import com.debug.kill.api.response.BaseResponse;
import com.debug.kill.model.dto.KillSuccessUserInfo;
import com.debug.kill.model.mapper.ItemKillSuccessMapper;
import com.debug.kill.server.dto.KillDto;
import com.debug.kill.server.service.KillService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * ClassName: KillController
 * Description: TODO
 * Author: Leo
 * Date: 2020/5/14-17:34
 * email 1437665365@qq.com
 */
@Controller
@Slf4j
public class KillController {

    @Autowired
    private KillService killService;

    private Gson gson =new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    private ItemKillSuccessMapper itemKillSuccessMapper;

    private static final String prefix = "kill";

    @RequestMapping(value = prefix+"/execute",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse execute(@RequestBody @Validated KillDto dto, BindingResult result, HttpSession session){
        if(result.hasErrors() ||dto.getKillId() <=0){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        Object uid = session.getAttribute("uid");
        if(uid==null){
            return new BaseResponse(StatusCode.UserNotLogin);
        }
        Integer userId = (Integer) uid;
        log.info("userId={}",userId);
        BaseResponse<Object> response = new BaseResponse<>(StatusCode.Success);
        try {
            //不加分布式锁
//            Boolean res = killService.killItem(dto.getKillId(), dto.getUserId());
//            if(!res){
//              return new BaseResponse(StatusCode.Fail.getCode(),"哈哈~~~商品已抢购完毕或者不在抢购的时间哦");
//            }
            //基于zookeeper的分布式锁进行控制
            Boolean res = killService.killItemV5(dto.getKillId(), userId);
            if(!res){
                return new BaseResponse(StatusCode.Fail.getCode(),
                        "不加分布式锁--基于zookeeper--哈哈~~~商品已抢购完毕或者不在抢购的时间哦");
            }
        }catch (Exception e){
             response = new BaseResponse<>(StatusCode.Fail.getCode(), e.getMessage());
        }

        return response;
    }

    //抢购成功跳转页面
    @RequestMapping(value = prefix+"/execute/success",method = RequestMethod.GET)
    public String success(){
       return "executeSuccess";
    }

    //抢购失败跳转页面
    @RequestMapping(value = prefix+"/execute/fail",method = RequestMethod.GET)
    public String fail(){
        return "executeFail";
    }

    //根据订单编号查询订单详情
    @RequestMapping(value = prefix+"/record/detail/{orderNo}",method = RequestMethod.GET)
    public String killRecordDetail(@PathVariable("orderNo") String orderNo, ModelMap modelMap){
        if(StringUtils.isBlank(orderNo)){
            return "errorFour";
        }
        KillSuccessUserInfo info = itemKillSuccessMapper.selectByCode(orderNo);
        if(info == null){
            return "errorFour";
        }
        modelMap.put("info",info);
        log.info("订单详情={}",gson.toJson(info));
        return "killRecord";
    }


}
