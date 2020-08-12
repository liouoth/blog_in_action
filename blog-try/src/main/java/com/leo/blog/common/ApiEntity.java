package com.leo.blog.common;

import com.alibaba.fastjson.JSONObject;

public class ApiEntity {
    private Integer code;
    private String msg;
    private Object data;

    public static ApiEntity success(){
        return new ApiEntity(200,"执行成功！",null);
    }

    public static ApiEntity failure(){
        return new ApiEntity(500,"执行失败！",null);
    }

    public static ApiEntity success(Object data){
        return new ApiEntity(200,"执行成功！",data);
    }

    public ApiEntity(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
