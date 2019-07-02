package com.coding.sales.entity;

import com.coding.sales.commons.DiscountType;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Commodity {
    /**
     * 商品编号
     */
    private String commodityNo;
    /**
     * 商品单位
     */
    private String commodityUnit;
    /**
     * 商品名称
     */
    private String commodityName;
    /**
     * 商品价格
     */
    private BigDecimal commodityPrice;
    /**
     * 商品参与的优惠活动列表
     */
    private List<String> discountList;

    public Commodity(String commodityNo, String commodityUnit, String commodityName, BigDecimal commodityPrice, List<String> discountList) {
        this.commodityNo = commodityNo;
        this.commodityUnit = commodityUnit;
        this.commodityName = commodityName;
        this.commodityPrice = commodityPrice;
        this.discountList = discountList;
    }

    public String getCommodityNo() {
        return commodityNo;
    }

    public void setCommodityNo(String commodityNo) {
        this.commodityNo = commodityNo;
    }

    public String getCommodityUnit() {
        return commodityUnit;
    }

    public void setCommodityUnit(String commodityUnit) {
        this.commodityUnit = commodityUnit;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public BigDecimal getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(BigDecimal commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public List<String> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<String> discountList) {
        this.discountList = discountList;
    }

    /**
     * 计算商品的优惠
     * @param count 购买商品的数量
     * @return 返回商品的优惠信息以及使用的折扣券信息
     */
    public Map<String,Object> discount(BigDecimal count){
        Map<String,Object> discountMap=new HashMap<String, Object>();
        BigDecimal discontAmount=new BigDecimal(0);
        String discountDesc=null;
        for(int i=0;discountList!=null && i<discountList.size();i++){
            DiscountInfo discountInfo=CommonCache.DISCOUNT_INFO_CONVERT.get(discountList.get(i));
            //金额满减
            if(discountInfo.getDiscountType().equals(DiscountType.MSUBTRACT)){
                BigDecimal discountAmount=discountInfo.discountAmount(count.multiply(commodityPrice));
                if(discontAmount.compareTo(discountAmount)<0){
                    discontAmount=discountAmount;
                    discountMap.remove("discountDesc");
                }
            }else if(discountInfo.getDiscountType().equals(DiscountType.ASUBTRACT)){
                //数量满减
               BigDecimal discount= discountInfo.discountCount(count);
               if(discontAmount.compareTo(discount.multiply(commodityPrice))<0){
                   discontAmount=discount.multiply(commodityPrice);
                   discountMap.remove("discountDesc");
               }
            }else if(discountInfo.getDiscountType().equals(DiscountType.DISCOUNT)){
                //折扣
                BigDecimal totalAmount=count.multiply(commodityPrice);
                BigDecimal discountRate=discountInfo.getDiscountRate().setScale(2,BigDecimal.ROUND_HALF_UP);
                BigDecimal amount=totalAmount.multiply(discountRate);
                BigDecimal discountAmount=totalAmount.subtract(amount);
                if(discontAmount.compareTo(discountAmount)<0){
                    discontAmount=discountAmount;
                    discountDesc=discountInfo.getDiscountDesc();
                    if(discountDesc!=null){
                        discountMap.put("discountDesc",discountDesc);
                    }
                }
            }
        }
        DiscountItemRepresentation discountItemRepresentation=new DiscountItemRepresentation(commodityNo,commodityName,discontAmount);
        discountMap.put("discountItemRepresentation",discountItemRepresentation);
        return discountMap;
    }
}
