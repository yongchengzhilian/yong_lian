package com.aidou.util.enums;

import com.aidou.util.entity.ProductItemVo;
import com.aidou.util.tool.Money;

/**
 * 商品枚举
 * Created by yingjiafeng on 2019/5/13.
 */
public enum ProductItemEnum {


    PRODUCT_ITEM_1(1L, new ProductItemVo("红线", "19.8",1));

    /**
     * 商品ID
     */
    private Long itemId;

    /**
     * 商品属性
     */
    private ProductItemVo productItemVo;

    ProductItemEnum(Long itemId, ProductItemVo productItemVo) {
        this.itemId = itemId;
        this.productItemVo = productItemVo;
    }


    /**
     * 根据商品ID获取商品价格(分)
     *
     * @param itemId 商品ID
     * @return
     */
    public  static Long findProductPriceByFen(Long itemId) {
        switch (itemId.intValue()) {
            case 1:
                return new Money(PRODUCT_ITEM_1.productItemVo.getPrice()).getCent();

        }
        return -1L;
    }


    /**
     * 根据商品ID获取商品价格(分)
     *
     * @param itemId 商品ID
     * @return
     */
    public  static ProductItemVo findProductPriceByVo(Long itemId) {
        switch (itemId.intValue()) {
            case 1:
                return  PRODUCT_ITEM_1.productItemVo;
        }
        return null;
    }


}
