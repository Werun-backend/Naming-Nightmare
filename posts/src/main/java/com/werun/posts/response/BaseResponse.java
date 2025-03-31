package com.werun.posts.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public static <T> BaseResponse<T> success(){
        BaseResponse<T> resultData=new BaseResponse<T>();
        resultData.setMsg("操作成功");
        resultData.setCode(10000);
        return resultData;
    }

    public static <T> BaseResponse<T> success(String msg){
        BaseResponse<T> resultData=new BaseResponse<T>();
        resultData.setMsg(msg);
        resultData.setCode(10000);
        return resultData;
    }

    public static <T> BaseResponse<T> success(T data){
        BaseResponse<T> resultData=new BaseResponse<T>();
        resultData.setData(data);
        resultData.setMsg("操作成功");
        resultData.setCode(10000);
        return resultData;
    }

    public static <T> BaseResponse<T> success(T data,String msg){
        BaseResponse<T> resultData=new BaseResponse<T>();
        resultData.setData(data);
        resultData.setMsg(msg);
        resultData.setCode(10000);
        return resultData;
    }

    /**
     * @Description:
     */
    public static <T> BaseResponse<T> error() {
        BaseResponse<T> resultData = new BaseResponse<>();
        resultData.setCode(500);
        resultData.setMsg("系统异常");
        return resultData;
    }

    /**
     * @Description:
     */
    public static <T> BaseResponse<T> error(String msg) {
        BaseResponse<T> resultData = new BaseResponse<>();
        resultData.setCode(500);
        resultData.setMsg(msg);
        return resultData;
    }

    /**
     * @Description:
     */
    public static <T> BaseResponse<T> error(int code,String msg) {
        BaseResponse<T> resultData = new BaseResponse<>();
        resultData.setCode(code);
        resultData.setMsg(msg);
        return resultData;
    }

}
