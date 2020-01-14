package com.aidou.util.tool;

import java.util.Calendar;
import java.util.Date;

public class UserDateUtil {

    private final static int[] dayArr = new int[] { 20, 19, 21, 20, 21, 22, 23,
            23, 23, 24, 23, 22 };
    private final static String[] constellationArr = new String[] { "摩羯座",
            "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座",
            "天蝎座", "射手座", "摩羯座" };

    /**
     * 通过生日计算属相
     *
     * @param year
     * @return
     */
    private static String getYear(int year) {
        if (year < 1900) {
            return "未知";
        }
        int start = 1900;
        String[] years = new String[] { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊",
                "猴", "鸡", "狗", "猪" };
        return years[(year - start) % years.length];
    }

    public static  String  findZodiac(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int i = cal.get(Calendar.YEAR);
        return  getYear(i);

    }


    public static  String  findConstellation(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int i = cal.get(Calendar.DAY_OF_MONTH);
        int i1 = cal.get(Calendar.MONTH)+1;
        return   getConstellation(i1,i);

    }

    /**
     * Java通过生日计算星座
     *
     * @param month
     * @param day
     * @return
     */
    private static String getConstellation(int month, int day) {
        return day < dayArr[month - 1] ? constellationArr[month - 1]
                : constellationArr[month];
    }




    public static int getAgeByBirth(Date birthday) {
        int age = 0;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());// 当前时间

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (birth.after(now)) {//如果传入的时间，在当前时间的后面，返回0岁
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {//兼容性更强,异常后返回数据
            return 0;
        }
    }


    /**
     * 是否允许创建话题
     * @return
     */
    public static  boolean   isCreateArticleByDate(Date  date){
        if (date==null){
            return true;
        }
        long time = date.getTime();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis-time>86400000){
            return true;
        }
        return false;


    }

}
