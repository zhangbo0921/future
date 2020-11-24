package com.qinghe.future.analysis;

public enum AnalysisType {

    ORDER_TYPE("工单类型",1),
    HOT_TYPE("热点分类",2),
    ADDR("事发地",3),
    TIME("事发日期",4);


    private String type;
    private Integer code;

    AnalysisType(String type, Integer code){
        this.type = type;
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
