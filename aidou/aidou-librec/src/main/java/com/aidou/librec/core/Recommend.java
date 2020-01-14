package com.aidou.librec.core;

import com.aidou.librec.vo.Set;
import com.aidou.librec.vo.UserSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;


public class Recommend {

    /**
     * 在给定username的情况下，计算其他用户和它的距离并排序
     * @param username
     * @param set
     * @return
     */
    private TreeMap<Double, String> computeNearestNeighbor(String username, UserSet set) {
        TreeMap<Double, String> distances = new TreeMap<Double, String>(new Comparator<Double>(){
            /*
             * int compare(Object o1, Object o2) 返回一个基本类型的整型，
             * 返回负数表示：o1 小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
             */
            @Override
            public int compare(Double o1, Double o2) {
                //指定排序器按照降序排列
                return o2.compareTo(o1);
            }
        });
        UserSet.User u1 = set.getUser(username);
        for (int i = 0; i < set.users.size(); i++) {
            UserSet.User u2 = set.getUser(i);
            if (!u2.username.equals(username)) {
                double distance = pearson_dis(u2.list, u1.list);
                distances.put(distance, u2.username);
            }

        }
        return distances;
    }
 
 
    /**
     *  计算2个打分序列间的pearson距离
     *
     * @param rating1
     * @param rating2
     * @return
     */
    private double pearson_dis(List<Set> rating1, List<Set> rating2) {
        int sum_xy = 0;
        int sum_x = 0;
        int sum_y = 0;
        double sum_x2 = 0;
        double sum_y2 = 0;
        int n = 0;
        for (int i = 0; i < rating1.size(); i++) {
            Set key1 = rating1.get(i);
            for (int j = 0; j < rating2.size(); j++) {
               Set key2 = rating2.get(j);
                if (key1.username.equalsIgnoreCase(key2.username)) {
                    n += 1;
                    double x = key1.score;
                    double y = key2.score;
                    sum_xy += x * y;
                    sum_x += x;
                    sum_y += y;
                    sum_x2 += Math.pow(x, 2);
                    sum_y2 += Math.pow(y, 2);
                }
 
            }
        }
        double denominator = Math.sqrt(sum_x2 - Math.pow(sum_x, 2) / n) * Math.sqrt(sum_y2 - Math.pow(sum_y, 2) / n);
        if (denominator == 0) {
            return 0;
        } else {
            return (sum_xy - (sum_x * sum_y) / n) / denominator;
        }
    }
 
 
    public Map<Double, String> recommend(Long userId, UserSet set) {
        TreeMap<Double, String> distances = computeNearestNeighbor(userId.toString(), set);
        return distances;
    }


}
