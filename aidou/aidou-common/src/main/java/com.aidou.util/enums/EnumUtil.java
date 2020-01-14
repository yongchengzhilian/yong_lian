package com.aidou.util.enums;

/**
 * Copyright@www.lanhusoft.com.
 * Author:yingjiafeng
 * Date:2019/11/9
 * Description:
 */
public class EnumUtil {

    /**
     * 账户状态
     * @param code
     * @return
     */
    public static String getAccountNameByCode(Integer code) {
        if (code == AppStatusEnum.ACCOUNT_STATUS1.getIndex()) {
            return AppStatusEnum.ACCOUNT_STATUS1.getName();
        } else if (code == AppStatusEnum.ACCOUNT_STATUS5.getIndex()) {
            return AppStatusEnum.ACCOUNT_STATUS5.getName();
        } else if (code == AppStatusEnum.ACCOUNT_STATUS6.getIndex()) {
            return AppStatusEnum.ACCOUNT_STATUS6.getName();
        }else{
            return AppStatusEnum.ACCOUNT_STATUS1.getName();
        }
    }

    /**
     * 获取注销详情
     *
     * @param code
     * @return
     */
    public static String getLogoutValByCode(Integer code) {

        if (code == AppStatusEnum.LOGOUT_STATUS1.getIndex()) {
            return AppStatusEnum.LOGOUT_STATUS1.getName();
        } else if (code == AppStatusEnum.LOGOUT_STATUS2.getIndex()) {
            return AppStatusEnum.LOGOUT_STATUS2.getName();
        } else if (code == AppStatusEnum.LOGOUT_STATUS3.getIndex()) {
            return AppStatusEnum.LOGOUT_STATUS3.getName();
        } else if (code == AppStatusEnum.LOGOUT_STATUS4.getIndex()) {
            return AppStatusEnum.LOGOUT_STATUS4.getName();
        } else if (code == AppStatusEnum.LOGOUT_STATUS5.getIndex()) {
            return AppStatusEnum.LOGOUT_STATUS5.getName();
        } else if (code == AppStatusEnum.LOGOUT_STATUS6.getIndex()) {
            return AppStatusEnum.LOGOUT_STATUS6.getName();
        } else {
            return AppStatusEnum.LOGOUT_STATUS6.getName();
        }


    }


}
