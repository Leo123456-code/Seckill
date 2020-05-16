package com.debug.kill.server.enums;

/**
 * ClassName: SysConstant
 * Description: TODO
 * Author: Leo
 * Date: 2020/5/14-17:57
 * email 1437665365@qq.com
 */
public class SysConstant {

    public enum OrderStatus{

        Invalid(-1,"无效"),
        SuccessNotPayed(0,"成功-未付款"),
        HasPayed(1,"已付款"),
        Cancel(2,"已取消"),

        ;

        private Integer code;
        private String msg;

        OrderStatus(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

}
