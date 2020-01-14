package com.aidou.librec.service.impl;

import com.aidou.librec.core.Recommend;
import com.aidou.librec.enums.*;
import com.aidou.librec.service.RecommendService;
import com.aidou.librec.vo.UserSet;
import com.aidou.librec.vo.UserVo;
import com.aidou.util.exception.BizException;
import com.aidou.util.tool.GsonUtil;
import com.aidou.util.tool.UserDateUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by yingjiafeng on 2019/5/21.
 */
@Service
public class RecommendServiceImpl implements RecommendService {


    @Override
    public List<Long> findMostFriendByUserMap(UserVo myVo, List<UserVo> userVoList) throws BizException {
        Optional.ofNullable(myVo).orElseThrow(NullPointerException::new);
        if (userVoList.isEmpty()) {
            throw new BizException("适合用户不存在");
        }
        Recommend recommend = new Recommend();
        UserSet userSet = getUserSet(myVo, userVoList);
        Map<Double, String> storeMap = recommend.recommend(myVo.getUid(), userSet);
        //获取前10的用户ID
        List<Long> collect = storeMap.entrySet().
                stream().
                map((e) -> Long.parseLong(e.getValue())).
                limit(5).
                collect(Collectors.toList());

        return collect;
    }

    private UserSet getUserSet(UserVo uid, List<UserVo> userVoList) {
        Integer sex = userVoList.get(0).getSex();
        if (sex == 0) {
            return getFamaleUserSet(uid, userVoList);
        } else {
            return getMaleUsersET(uid, userVoList);
        }
    }

    private UserSet getMaleUsersET(UserVo vo, List<UserVo> userVoList) {
        UserSet userSet = new UserSet();
        userSet.put(vo.getUid() + "")
                .set("身高", FemaleHeightRecommendEnum.getStore(vo.getHeight()))
                .set("体重", FemaleWeightRecommendEnum.getStore(vo.getWeight()))
                .set("年龄", FemaleAgeRecommendEnum.getStore(UserDateUtil.getAgeByBirth(vo.getBirthday())))
                .set("收入", FemaleIncomeRecommendEnum.getstore(vo.getIncome()))
                .set("学历", EducationRecommendEnum.getStore(vo.getEducation()))
                .set("车房", CarHouseRecommendEnum.getStore(vo.getHouseCar()))
                .set("婚姻状态", MarriageRecommendEnum.getStore(vo.getMarriage()))
                .set("系数", getCoefficient())
                .create();

        userVoList.forEach((x) -> {
            int age = 30;
            if (x.getBirthday() != null) {
                age = UserDateUtil.getAgeByBirth(x.getBirthday());
            }
            userSet.put(x.getUid() + "")
                    .set("身高", MaleHeightRecommendEnum.getStore(x.getHeight()))
                    .set("体重", MaleWeightRecommendEnum.getStore(x.getWeight()))
                    .set("年龄", MaleAgeRecommendEnum.getStore(age))
                    .set("收入", MaleIncomeRecommendEnum.getstore(x.getIncome()))
                    .set("学历", EducationRecommendEnum.getStore(x.getEducation()))
                    .set("车房", CarHouseRecommendEnum.getStore(x.getHouseCar()))
                    .set("婚姻状态", MarriageRecommendEnum.getStore(x.getMarriage()))
                    .set("系数", getCoefficient())
                    .create();
        });

        return userSet;
    }

    private UserSet getFamaleUserSet(UserVo vo, List<UserVo> userVoList) {
        UserSet userSet = new UserSet();
        userSet.put(vo.getUid() + "")
                .set("身高", MaleHeightRecommendEnum.getStore(vo.getHeight()))
                .set("体重", MaleWeightRecommendEnum.getStore(vo.getWeight()))
                .set("年龄", MaleAgeRecommendEnum.getStore(UserDateUtil.getAgeByBirth(vo.getBirthday())))
                .set("收入", MaleIncomeRecommendEnum.getstore(vo.getIncome()))
                .set("学历", EducationRecommendEnum.getStore(vo.getEducation()))
                .set("车房", CarHouseRecommendEnum.getStore(vo.getHouseCar()))
                .set("婚姻状态", MarriageRecommendEnum.getStore(vo.getMarriage()))
                .set("系数", getCoefficient())
                .create();

        userVoList.forEach((x) -> {
            int age = 30;
            if (x.getBirthday() != null) {
                age = UserDateUtil.getAgeByBirth(x.getBirthday());
            }
            userSet.put(x.getUid() + "")
                    .set("身高", FemaleHeightRecommendEnum.getStore(x.getHeight()))
                    .set("体重", FemaleWeightRecommendEnum.getStore(x.getWeight()))
                    .set("年龄", FemaleAgeRecommendEnum.getStore(age))
                    .set("收入", FemaleIncomeRecommendEnum.getstore(x.getIncome()))
                    .set("学历", EducationRecommendEnum.getStore(x.getEducation()))
                    .set("车房", CarHouseRecommendEnum.getStore(x.getHouseCar()))
                    .set("婚姻状态", MarriageRecommendEnum.getStore(x.getMarriage()))
                    .set("系数", getCoefficient())
                    .create();
        });

        return userSet;

    }

    public double  getCoefficient(){
        double min = 0.0001;
        double max = 2;
        int scl =  4;
        int pow = (int) Math.pow(10, scl);
       return Math.floor((Math.random() * (max - min) + min) * pow) / pow;
    }
}
