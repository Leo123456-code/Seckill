package com.debug.kill.server.controller;

import com.debug.kill.model.entity.ItemKill;
import com.debug.kill.server.service.ItemService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * ClassName: ItemController
 * Description: TODO
 * Author: Leo
 * Date: 2020/5/14-16:16
 * email 1437665365@qq.com
 */
@Controller
@Slf4j
public class ItemController {

    @Autowired
    private ItemService itemService;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static final String prefix = "item";

    @RequestMapping(value = {"/","/index",prefix+"/list",prefix+"/index.html"},method = RequestMethod.GET)
    public String list(ModelMap modelMap){
        //获取待秒杀的商品列表
        try {

            List<ItemKill> list = itemService.getKillItems();
            modelMap.put("list",list);

            log.info("获取待秒杀的商品列表-数据={}",gson.toJson(list));
        }catch (Exception e){
            log.error("获取待秒杀的商品列表异常",e.fillInStackTrace());
            return "redirect:/base/errorFour";
        }
        return "list";
    }

    @RequestMapping(value = prefix+"/detail/{id}",method = RequestMethod.GET)
    public String detail(@PathVariable Integer id,ModelMap modelMap){
        if(id == null || id <= 0){
            return "redirect:/base/errorFour";
        }
        //获取商品详情
        try {
            ItemKill detail = itemService.getKillDetail(id);
            modelMap.put("detail",detail);
            log.info("获取待秒杀的商品详情-数据={}",gson.toJson(detail));
        }catch (Exception e){
            log.error("获取待秒杀的商品详情异常,id={}",id,e.fillInStackTrace());
            return "redirect:/base/errorFour";
        }
        return "info";
    }



}
