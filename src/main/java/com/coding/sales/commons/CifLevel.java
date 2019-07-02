package com.coding.sales.commons;

import java.util.HashMap;
import java.util.Map;

public enum CifLevel {
    /**
     * 普卡
     */
    ORDINARY("普卡"),
    /**
     * 金卡
     */
    GOLD("金卡"),
    /**
     * 白金卡
     */
    PLATINUM("白金卡"),
    /**
     * 钻石卡
     */
    DIAMONDS("钻石卡");
    /**
     * 值
     */
    private String value;

    CifLevel(String value) {
        this.value = value;
    }

    public String getEnumValue() {
        return value;
    }

    /**
     * map对象
     */
    private static Map<String, CifLevel> valueMap = new HashMap();

    /**
     * 静态方法
     */
    static {
        for (CifLevel en : CifLevel.values()) {
            valueMap.put(en.value, en);
        }
    }

    /**
     * 从具体值获取枚举值
     *
     * @param value 值
     * @return en
     */
    public static CifLevel getEnumByValue(String value) {
        return valueMap.get(value);
    }
}
