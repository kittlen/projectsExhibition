package com.schoolProjects.entity;

import com.google.gson.Gson;

/**
 * @author kittlen
 * @version 1.0
 * @date 2020/8/26 16:33
 */
public class Result {

    public static final int RESULT_OK=1; //成功
    public static final int RESULT_ERROR=-9999; //失败
    public static final int RESULT_ZERO=0; // 成功没有数据


    private  static Gson gson=new Gson();

    private int code;//编码
    private long count;//计数
    private Object data;//数据
    private String msg;//提示消息

    public Result() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setDataJson(Object data){
        this.data=gson.toJson(data);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static String toJson(Result result){
        return gson.toJson(result);
    }

    public static Result toResult(String reusltJson){
        return gson.fromJson(reusltJson, Result.class);
    }

}
