package com.debug.kill.server.controller;


import com.debug.kill.api.enums.StatusCode;
import com.debug.kill.api.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试
 *
 * @Author:Leo
 * @Date: 2019/6/13 23:36
 **/
@Controller
@RequestMapping("/base")
@Slf4j
public class BaseController {


    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(String name, ModelMap modelMap) {
        if (StringUtils.isBlank(name)) {
            name = "这是welcome";
        }
        modelMap.put("name", name);
        return "welcome";
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    @ResponseBody
    public String data(String name) {
        if (StringUtils.isBlank(name)) {
            name = "这是welcome";
        }
        return name;
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse success(String name) {
        BaseResponse baseResponse = new BaseResponse<>(StatusCode.Success);

        if (StringUtils.isBlank(name)) {
            name = "这是welcome";
        }
        baseResponse.setData(name);
        return baseResponse;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {

        return "errorFour";
    }


}























