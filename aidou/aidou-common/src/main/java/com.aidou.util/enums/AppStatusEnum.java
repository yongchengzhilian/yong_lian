package com.aidou.util.enums;

/**
 * -1:审核拒绝  1:通过 2：待审核
 *
 * @author yingjiafeng
 * @date 2019/5/13
 */
public enum AppStatusEnum {


    /**
     * 账户状态
     */
    ACCOUNT_STATUS1("正常", 1),
    ACCOUNT_STATUS5("注销", 5),
    ACCOUNT_STATUS6("隐藏", 6),


    /**
     * 订单状态
     */
    ORDER_STATUS1("未支付", 1),
    ORDER_STATUS2("已支付", 2),
    ORDER_STATUS3("订单异常", -1),


    /**
     * 账户状态
     * 状态1：发送  -1 作废  2：牵手后拒绝  3：牵手中  4:被拒绝
     */
    APPLICATION_STATUS1("申请", 1),
    APPLICATION_STATUS2("牵手后拒绝", 2),
    APPLICATION_STATUS3("牵手中", 3),
    APPLICATION_STATUS4("被拒绝", 4),


    /**
     * 审核
     */
    CHECK_ING("待审核", 2),
    CHECK("审核通过", 1),
    CHECK_FAIL("审核拒绝", -1),
    /**
     * 注销原因
     */
    LOGOUT_STATUS1("下载后发现不符合使用需求", 1),
    LOGOUT_STATUS2("操作界面杂乱", 2),
    LOGOUT_STATUS3("已找到另一半", 3),
    LOGOUT_STATUS4("对于内容不感到兴趣", 4),
    LOGOUT_STATUS5("产品卡顿", 5),
    LOGOUT_STATUS6("其他", 6);

    private String name;
    private Integer index;

     AppStatusEnum(String name, Integer index) {
        this.setName(name);
        this.setIndex(index);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return this.index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

}
