package com.coding.sales.commons;

import java.util.HashMap;
import java.util.Map;

public enum DiscountType {
    /**
     * 金额满减
     */
    MSUBTRACT("MSUBTRACT"),
    /**
     * 打折
     */
    DISCOUNT("DISCOUNT"),
    /**
     * 数量满减
     */
    ASUBTRACT("ASUBTRACT");
    /**
     * 值
     */
    private String value;

    DiscountType(String value) {
        this.value = value;
    }

    public String getEnumValue() {
        return value;
    }

    /**
     * map对象
     */
    private static Map<String, DiscountType> valueMap = new HashMap();

    /**
     * 静态方法
     */
    static {
        for (DiscountType en : DiscountType.values()) {
            valueMap.put(en.value, en);
        }
    }

    /**
     * 从具体值获取枚举值
     *
     * @param value 值
     * @return en
     */
    public static DiscountType getEnumByValue(String value) {
        return valueMap.get(value);
    }
}
