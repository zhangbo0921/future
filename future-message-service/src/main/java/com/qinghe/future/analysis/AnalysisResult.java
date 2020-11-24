package com.qinghe.future.analysis;

import lombok.Data;

@Data
public class AnalysisResult {
    private String type;
    private String value;

    private AnalysisResult(){}

    public AnalysisResult(AnalysisType type,String value){
         this.type = type.getType();
         this.value = value;
    }
}
