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
 * 工单类型分析
 */
@Service
public class OrderTypeAnalysis implements AnalysisService {

    public static final Integer TOUSU_SCORE = 1000;
    public static final Integer QIUZHU_SCORE = 900;
    public static final Integer ZIXUN_SCORE = 800;
    public static final Integer JIANYI_SCORE = 700;
    public static final Integer BIAOYANG_SCORE = 600;

    private static Map<String, List<String>> map = new HashMap<>();

    static {
        //求助
        List<String> qiuzhu = new ArrayList<>();
        qiuzhu.add("求助");
        qiuzhu.add("乞助");
        qiuzhu.add("告急");
        qiuzhu.add("求援");
        qiuzhu.add("请求");
        qiuzhu.add("请求");
        qiuzhu.add("求救");
        qiuzhu.add("反映");
        qiuzhu.add("协调");
        qiuzhu.add("咋办");
        qiuzhu.add("如何处理");
        qiuzhu.add("解决不了");
        qiuzhu.add("能不能");
        map.put("求助",qiuzhu);
        //建议
        List<String> jianyi = new ArrayList<>();
        jianyi.add("建议");
        jianyi.add("提议");
        jianyi.add("意见");
        jianyi.add("好想法");
        jianyi.add("创议");
        jianyi.add("建议");
        jianyi.add("倡议");
        jianyi.add("提倡");
        jianyi.add("提出");
        jianyi.add("发起");
        jianyi.add("倡导");
        map.put("建议",jianyi);
        //投诉
        List<String> tousu = new ArrayList<>();
        tousu.add("投诉");
        tousu.add("举报");
        tousu.add("申诉");
        tousu.add("申述");
        tousu.add("诉求");
        tousu.add("告发");
        tousu.add("检举");
        tousu.add("给个说法");
        tousu.add("给个答复");
        tousu.add("恶劣");
        tousu.add("官腔很重");
        tousu.add("不好");
        tousu.add("很差");
        tousu.add("很凶");
        tousu.add("很牛");
        tousu.add("牛气哄哄");
        tousu.add("没有结果");
        tousu.add("不办事");
        map.put("投诉",tousu);
        //咨询
        List<String> zixunlei = new ArrayList<>();
        zixunlei.add("咨询");
        zixunlei.add("问问");
        zixunlei.add("问一下");
        zixunlei.add("怎么办");
        zixunlei.add("是什么");
        zixunlei.add("哪里");
        zixunlei.add("是多少");
        zixunlei.add("询问");
        zixunlei.add("参谋");
        zixunlei.add("筹商");
        zixunlei.add("研究");
        zixunlei.add("商酌");
        zixunlei.add("磋议");
        zixunlei.add("磋商");
        zixunlei.add("商议");
        zixunlei.add("商讨");
        zixunlei.add("商量");
        zixunlei.add("盘问");
        zixunlei.add("讨论");
        zixunlei.add("斟酌");
        zixunlei.add("接洽");
        zixunlei.add("征询");
        zixunlei.add("需要什么");
        zixunlei.add("拿什么");
        zixunlei.add("准备什么");
        zixunlei.add("带什么");
        map.put("咨询",zixunlei);
        //表扬
        List<String> biaoyang = new ArrayList<>();
        biaoyang.add("表扬");
        biaoyang.add("歌颂");
        biaoyang.add("赞扬");
        biaoyang.add("称誉");
        biaoyang.add("称道");
        biaoyang.add("赞美");
        biaoyang.add("陈赞");
        biaoyang.add("赞颂");
        biaoyang.add("称赞");
        biaoyang.add("夸奖");
        biaoyang.add("褒奖");
        biaoyang.add("赞叹");
        biaoyang.add("褒扬");
        biaoyang.add("赞赏");
        biaoyang.add("赞誉");
        biaoyang.add("颂扬");
        biaoyang.add("表彰");
        biaoyang.add("送锦旗");
        biaoyang.add("真好");
        biaoyang.add("非常感谢");
        biaoyang.add("特好");
        biaoyang.add("很好");
        map.put("表扬",biaoyang);
    }
    @Override
    public AnalysisResult analysisText(String text) {
        String type = null;
        Integer score = 0;
        for (String key :map.keySet()){
            List<String> collect = map.get(key).stream().filter(item -> {
                if (text.contains(item)) {
                    return true;
                } else {
                    return false;
                }
            }).collect(Collectors.toList());
            if(collect!=null && collect.size()>0){
                if(key.equals("投诉")){
                    if(score==0){
                        score = TOUSU_SCORE;
                        type = "投诉";
                    }else{
                        if (score<TOUSU_SCORE) {
                            score = TOUSU_SCORE;
                            type = "投诉";
                        }
                    }
                }
                if(key.equals("求助")){
                    if(score==0){
                        score = QIUZHU_SCORE;
                        type = "求助";
                    }else{
                        if (score<QIUZHU_SCORE) {
                            score = QIUZHU_SCORE;
                            type = "求助";
                        }
                    }
                }
                if(key.equals("咨询")){
                    if(score==0){
                        score = ZIXUN_SCORE;
                        type = "咨询";
                    }else{
                        if (score<ZIXUN_SCORE) {
                            score = ZIXUN_SCORE;
                            type = "咨询";
                        }
                    }
                }
                if(key.equals("建议")){
                    if(score==0){
                        score = JIANYI_SCORE;
                        type = "建议";
                    }else{
                        if (score<JIANYI_SCORE) {
                            score = JIANYI_SCORE;
                            type = "建议";
                        }
                    }
                }
                if(key.equals("表扬")){
                    if(score==0){
                        score = BIAOYANG_SCORE;
                        type = "表扬";
                    }else{
                        if (score<BIAOYANG_SCORE) {
                            score = BIAOYANG_SCORE;
                            type = "表扬";
                        }
                    }
                }
            }
        }
        return type==null?null:new AnalysisResult(AnalysisType.ORDER_TYPE,type);
    }

    public static Integer getScore(String type){
        Integer score = 0;
        if(type.equals("投诉")){
            score = TOUSU_SCORE;
        }
        if(type.equals("求助")){
            score = QIUZHU_SCORE;
        }
        if(type.equals("咨询")){
            score = ZIXUN_SCORE;
        }
        if(type.equals("建议")){
            score = JIANYI_SCORE;
        }
        if(type.equals("表扬")){
            score = BIAOYANG_SCORE;
        }
        return score;
    }
}
