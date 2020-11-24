package com.qinghe.future.analysis.service;

import com.qinghe.future.analysis.AnalysisResult;
import com.qinghe.future.analysis.AnalysisService;
import com.qinghe.future.analysis.AnalysisType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * 热点分析
 */
@Service
public class HotAnalysis implements AnalysisService {

    @Override
    public AnalysisResult analysisText(String text) {

        return new AnalysisResult(AnalysisType.HOT_TYPE,"市政-天然气");
    }
}
