package com.qinghe.future.analysis.service;

import com.qinghe.future.analysis.AnalysisResult;
import com.qinghe.future.analysis.AnalysisService;
import com.qinghe.future.analysis.AnalysisType;
import org.springframework.stereotype.Service;

/**
 * 地址分析
 */
@Service
public class AddrAnalysis implements AnalysisService {

    @Override
    public AnalysisResult analysisText(String text) {

        return new AnalysisResult(AnalysisType.ADDR,"佳贝大厦");
    }
}
