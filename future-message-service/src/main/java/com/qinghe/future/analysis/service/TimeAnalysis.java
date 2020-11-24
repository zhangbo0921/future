package com.qinghe.future.analysis.service;

import com.qinghe.future.analysis.AnalysisResult;
import com.qinghe.future.analysis.AnalysisService;
import com.qinghe.future.analysis.AnalysisType;
import org.springframework.stereotype.Service;
/**
 * 事发时间分析
 */
@Service
public class TimeAnalysis implements AnalysisService {

    @Override
    public AnalysisResult analysisText(String text) {

        return new AnalysisResult(AnalysisType.TIME,"2020-09-25");
    }
}
