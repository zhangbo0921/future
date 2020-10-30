package com.qinghe.future.commons.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {

    private int code;
    private String message;
    private T data;

    public Result(int code,String message){
        this(code,message,null);
    }
    public Result(ResultType commonResultType){
        this(commonResultType.getCode(),commonResultType.getMessage(),null);
    }
    public Result(ResultType commonResultType,T data){
        this(commonResultType.getCode(),commonResultType.getMessage(),data);
    }
}
