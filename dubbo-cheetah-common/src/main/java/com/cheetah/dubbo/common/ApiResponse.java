package com.cheetah.dubbo.common;

/**
 * Description: 返回前端的数据格式
 * @author jason
 * 2018年10月25日
 * @version 1.0
 */
public class ApiResponse {

    private int status;

    private String msg;

    private Object data;

    public ApiResponse() {

    }
    
    public ApiResponse(Object data) {
        this.data = data;
    }
    
    public ApiResponse(int status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
