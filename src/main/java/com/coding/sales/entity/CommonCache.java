package com.coding.sales.entity;

import com.coding.sales.commons.CifLevel;
import com.coding.sales.commons.DiscountType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonCache {

    public static final Map<String, CustomerPoint> CUSTOMER_POINT_CONVERT = new HashMap<String, CustomerPoint>() {{
        put("6236609999",new CustomerPoint("马丁", CifLevel.ORDINARY,"6236609999", new BigDecimal(9860)));
        put("6630009999",new CustomerPoint("王立", CifLevel.GOLD,"6630009999", new BigDecimal(48860)));
        put("8230009999",new CustomerPoint("李想", CifLevel.PLATINUM,"8230009999", new BigDecimal(98860)));
        put("9230009999",new CustomerPoint("张三", CifLevel.DIAMONDS,"9230009999", new BigDecimal(198860)));
    }};
    public static final Map<String, Commodity> COMMODITY_CONVERT = new HashMap<String, Commodity>() {{
        put("001001",new Commodity("001001","册","世园会五十国钱币册"
                ,new BigDecimal(998),null));
        List<String> list1=new ArrayList<String>();
        list1.add("100001");
        List<String> list2=new ArrayList<String>();
        list2.add("100002");
        List<String> list3=new ArrayList<String>();
        list3.add("100006");
        list3.add("100007");
        List<String> list4=new ArrayList<String>();
        list4.add("100004");
        list4.add("100005");
        List<String> list5=new ArrayList<String>();
        list5.add("100002");
        list5.add("100006");
        list5.add("100007");
        List<String> list6=new ArrayList<String>();
        list6.add("100001");
        list6.add("100003");
        list6.add("100004");
        list6.add("100005");
        put("001002",new Commodity("001002","盒","2019北京世园会纪念银章大全40g"
                ,new BigDecimal(1380),list1));
        put("003001",new Commodity("003001","条","招财进宝"
                ,new BigDecimal(1580),list2));
        put("003002",new Commodity("003002","条","水晶之恋"
                ,new BigDecimal(980),list3));
        put("002002",new Commodity("002002","套","中国经典钱币套装"
                ,new BigDecimal(998),list4));
        put("002001",new Commodity("002001","条","守扩之羽比翼双飞4.8g"
                ,new BigDecimal(1080),list5));
        put("002003",new Commodity("002003","套","中国银象棋12g"
                ,new BigDecimal(698),list6));
    }};
    public static final Map<String, DiscountInfo> DISCOUNT_INFO_CONVERT = new HashMap<String, DiscountInfo>() {{
        put("100001",new DiscountInfo("100001", DiscountType.DISCOUNT,new BigDecimal(0.9),"9折券",
                new BigDecimal(0),new BigDecimal(0),new BigDecimal(0),new BigDecimal(0)));
        put("100002",new DiscountInfo("100002", DiscountType.DISCOUNT,new BigDecimal(0.95),"95折券",
                new BigDecimal(0),new BigDecimal(0),new BigDecimal(0),new BigDecimal(0)));
        put("100003",new DiscountInfo("100003", DiscountType.MSUBTRACT,new BigDecimal(0),"每满3000元减350",
                new BigDecimal(3000),new BigDecimal(350),new BigDecimal(0),new BigDecimal(0)));
        put("100004",new DiscountInfo("100004", DiscountType.MSUBTRACT,new BigDecimal(0),"每满2000元减30",
                new BigDecimal(2000),new BigDecimal(30),new BigDecimal(0),new BigDecimal(0)));
        put("100005",new DiscountInfo("100005", DiscountType.MSUBTRACT,new BigDecimal(0),"每满1000元减10",
                new BigDecimal(1000),new BigDecimal(10),new BigDecimal(0),new BigDecimal(0)));
        put("100006",new DiscountInfo("100006", DiscountType.ASUBTRACT,new BigDecimal(0),"第3件半价",
                new BigDecimal(0),new BigDecimal(0),new BigDecimal(3),new BigDecimal(0.5)));
        put("100007",new DiscountInfo("100007", DiscountType.ASUBTRACT,new BigDecimal(0),"满3送1",
                new BigDecimal(0),new BigDecimal(0),new BigDecimal(4),new BigDecimal(1)));
    }};
}
