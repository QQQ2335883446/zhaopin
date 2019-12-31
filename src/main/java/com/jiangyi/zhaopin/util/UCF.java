package com.jiangyi.zhaopin.util;

import com.jiangyi.zhaopin.model.Applicant;

import java.util.*;

//协同过滤算法
public class UCF {

    HashMap<Applicant,Double > doubleApplicantHashMap = new HashMap<>();

    //两个用户的条件相似度
    public double getNumByApp(Applicant a1,Applicant a2){
        int countSimilary = 0;
        //求职意向需一致
        if(a2.getuJob().contains(a1.getuJob())){
            //通过年龄，城市等条件获取相似度
            if(a1.getuAge().equals(a2.getuAge())){
                countSimilary++;
            }
            if(a1.getuCity().equals(a2.getuCity())){
                countSimilary++;
            }
            if(Math.abs((Integer.valueOf(a1.getuSalary().replaceAll("K",""))-Integer.valueOf(a2.getuSalary().replaceAll("K",""))))<2){
                countSimilary++;
            }
            if(a1.getuStatus().equals(a2.getuStatus())){
                countSimilary++;
            }
            if(a1.getuSex().equals(a2.getuSex())){
                countSimilary++;
            }
            //返回相似度
            return countSimilary/5.0;
        }

        return 0;
    }

    //取相似度超过70%，组成相似用户集合
    public List<Applicant> getMaxSimilarity(Applicant root,List<Applicant> applicants){
        for (Applicant applicant:applicants){
            double numByApp = getNumByApp(root, applicant);
            doubleApplicantHashMap.put(applicant,numByApp);
        }
        applicants.clear();
        for(Map.Entry<Applicant,Double> entry : doubleApplicantHashMap.entrySet()){
            if(entry.getValue()>0.7){
                applicants.add(entry.getKey());
            }
        }
        return applicants;
    }

}
