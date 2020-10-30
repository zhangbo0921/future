package com.qinghe.future.commons.result;

public enum ResultType {
    SUCCESS(200,"操作成功"),
    FAIL(300,"操作失败"),
    SAVE_SUCCESS(200,"保存成功"),
    SAVE_FAIL(300,"保存失败"),
    DELETE_SUCCESS(200,"删除成功"),
    DELETE_FAIL(300,"删除失败"),
    UPDATE_SUCCESS(200,"修改成功"),
    UPDATE_FAIL(300,"修改失败"),
    QUERY_SUCCESS(200,"查询成功"),
    QUERY_FAIL(300,"查询失败");

    private int code;
    private String message;
    ResultType(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
