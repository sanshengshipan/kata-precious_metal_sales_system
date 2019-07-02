package com.coding.sales.entity;

import com.coding.sales.commons.CifLevel;

import java.math.BigDecimal;

public class CustomerPoint {
    private String cifName;
    private CifLevel cifLevel;
    private String acNo;
    private BigDecimal points;

    public CustomerPoint(String cifName, CifLevel cifLevel, String acNo, BigDecimal points) {
        this.cifName = cifName;
        this.cifLevel = cifLevel;
        this.acNo = acNo;
        this.points = points;
    }

    public String getCifName() {
        return cifName;
    }

    public void setCifName(String cifName) {
        this.cifName = cifName;
    }

    public CifLevel getCifLevel() {
        return cifLevel;
    }

    public void setCifLevel(CifLevel cifLevel) {
        this.cifLevel = cifLevel;
    }

    public String getAcNo() {
        return acNo;
    }

    public void setAcNo(String acNo) {
        this.acNo = acNo;
    }

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }
    public void addPoint(BigDecimal amount){
        if(this.cifLevel.equals(CifLevel.ORDINARY)){
            this.points=points.add(amount);
        }else if(CifLevel.GOLD.equals(this.cifLevel)){
            this.points=points.add(amount.multiply(new BigDecimal(1.5)));
        }else if(CifLevel.PLATINUM.equals(this.cifLevel)){
            this.points=points.add(amount.multiply(new BigDecimal(1.8)));
        }else if(CifLevel.DIAMONDS.equals(this.cifLevel)){
            this.points=points.add(amount.multiply(new BigDecimal(2)));
        }
        updateCifLevel();
    }
    public void updateCifLevel(){
        if(this.points.compareTo(new BigDecimal(10000))<0){
            this.cifLevel=CifLevel.ORDINARY;
        }else if(this.points.compareTo(new BigDecimal(10000))>=0 &&
                this.points.compareTo(new BigDecimal(50000))<0){
            this.cifLevel=CifLevel.GOLD;
        }else if(this.points.compareTo(new BigDecimal(50000))>=0 &&
                this.points.compareTo(new BigDecimal(100000))<0){
            this.cifLevel=CifLevel.PLATINUM;
        }else{
            this.cifLevel=CifLevel.DIAMONDS;
        }
    }
}
