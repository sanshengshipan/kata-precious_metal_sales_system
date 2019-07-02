package com.coding.sales.entity;

import com.coding.sales.commons.DiscountType;

import java.math.BigDecimal;

public class DiscountInfo {
    private String discountNo;
    private DiscountType discountType;
    private BigDecimal discountRate;
    private String discountDesc;
    private BigDecimal satisfyAmount;
    private BigDecimal subtractAmount;
    private BigDecimal satisfyCount;
    private BigDecimal subtractCount;

    public DiscountInfo(String discountNo, DiscountType discountType, BigDecimal discountRate, String discountDesc,
                        BigDecimal satisfyAmount, BigDecimal subtractAmount, BigDecimal satisfyCount, BigDecimal subtractCount) {
        this.discountNo = discountNo;
        this.discountType = discountType;
        this.discountRate = discountRate;
        this.discountDesc = discountDesc;
        this.satisfyAmount = satisfyAmount;
        this.subtractAmount = subtractAmount;
        this.satisfyCount = satisfyCount;
        this.subtractCount = subtractCount;
    }

    public String getDiscountNo() {
        return discountNo;
    }

    public void setDiscountNo(String discountNo) {
        this.discountNo = discountNo;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public String getDiscountDesc() {
        return discountDesc;
    }

    public void setDiscountDesc(String discountDesc) {
        this.discountDesc = discountDesc;
    }

    public BigDecimal getSatisfyAmount() {
        return satisfyAmount;
    }

    public void setSatisfyAmount(BigDecimal satisfyAmount) {
        this.satisfyAmount = satisfyAmount;
    }

    public BigDecimal getSubtractAmount() {
        return subtractAmount;
    }

    public void setSubtractAmount(BigDecimal subtractAmount) {
        this.subtractAmount = subtractAmount;
    }

    public BigDecimal getSatisfyCount() {
        return satisfyCount;
    }

    public void setSatisfyCount(BigDecimal satisfyCount) {
        this.satisfyCount = satisfyCount;
    }

    public BigDecimal getSubtractCount() {
        return subtractCount;
    }

    public void setSubtractCount(BigDecimal subtractCount) {
        this.subtractCount = subtractCount;
    }

    public BigDecimal discountAmount(BigDecimal amount){
        BigDecimal discountAmount=new BigDecimal(0);
        if(amount.compareTo(satisfyAmount)>0){
            discountAmount=amount.divide(satisfyAmount,0,BigDecimal.ROUND_DOWN);
            discountAmount=discountAmount.multiply(subtractAmount);
        }
        return discountAmount;
    }
    public BigDecimal discountCount(BigDecimal count){
        BigDecimal discountAmount=new BigDecimal(0);
        if(count.compareTo(satisfyCount)>=0){
            discountAmount=subtractCount;
        }
        return discountAmount;
    }
}
