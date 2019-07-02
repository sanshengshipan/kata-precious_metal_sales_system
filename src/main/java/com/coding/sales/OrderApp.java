package com.coding.sales;

import com.coding.sales.entity.Commodity;
import com.coding.sales.entity.CommonCache;
import com.coding.sales.entity.CustomerPoint;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;
import com.coding.sales.output.OrderRepresentation;
import com.coding.sales.output.PaymentRepresentation;
import com.coding.sales.util.DateNbUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 销售系统的主入口
 * 用于打印销售凭证
 */
public class OrderApp {

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("参数不正确。参数1为销售订单的JSON文件名，参数2为待打印销售凭证的文本文件名.");
        }

        String jsonFileName = args[0];
        String txtFileName = args[1];

        String orderCommand = FileUtils.readFromFile(jsonFileName);
        OrderApp app = new OrderApp();
        String result = app.checkout(orderCommand);
        FileUtils.writeToFile(result, txtFileName);
    }

    public String checkout(String orderCommand) {
        OrderCommand command = OrderCommand.from(orderCommand);
        OrderRepresentation result = checkout(command);
        
        return result.toString();
    }

    OrderRepresentation checkout(OrderCommand command) {
        //第一步：计算优惠信息
        Map<String,Object> discountMap=getDiscountItemList(command);
        List<DiscountItemRepresentation> discountItemList=null;
        List<String> discountCards=null;
        if(discountMap!=null){
            discountItemList=(List<DiscountItemRepresentation>)discountMap.get("discountItemList");
            discountCards=(List<String>)discountMap.get("discountCards");
        }
        //优惠总金额
        BigDecimal totalDiscountPrice=getTotalDiscountPrice(discountItemList);
        //第二步：客户积分累计以及等级调整
        CustomerPoint customerPoint=CommonCache.CUSTOMER_POINT_CONVERT.get(command.getMemberId());
        //订单总金额
        BigDecimal totalPrice=getTotalPrice(command);
        String oldMemberType=customerPoint.getCifLevel().getEnumValue();
        BigDecimal oldPoints=customerPoint.getPoints();
        BigDecimal receivables=totalPrice.subtract(totalDiscountPrice);
        customerPoint.addPoint(receivables);
        int memberPointsIncreased=customerPoint.getPoints().subtract(oldPoints).intValue();
        //第三步：整理购买商品清单明细
        List<OrderItemRepresentation> orderItemList=getOrderItemList(command);
        //获取付款信息
        List<PaymentRepresentation> paymentList=new ArrayList<PaymentRepresentation>();
        PaymentRepresentation paymentRepresentation=new PaymentRepresentation("余额支付",receivables);
        paymentList.add(paymentRepresentation);
        OrderRepresentation result = new OrderRepresentation(command.getOrderId(), DateNbUtils.formatCompactDateTime(command.getCreateTime())
                ,customerPoint.getAcNo(), customerPoint.getCifName(),oldMemberType,customerPoint.getCifLevel().getEnumValue()
                ,memberPointsIncreased, customerPoint.getPoints().intValue(),orderItemList,totalPrice,discountItemList,totalDiscountPrice,
                receivables, paymentList,discountCards);
        return result;
    }

    /**
     * 获取优惠清单内容
     * @param command
     * @return
     */
    Map<String,Object> getDiscountItemList(OrderCommand command){
        Map<String,Object> discountItemMap=new HashMap<String, Object>();
        List<DiscountItemRepresentation> discountItemList=new ArrayList<DiscountItemRepresentation>();
        List<String> discountCards=new ArrayList<String>();
        for(int i=0;command.getItems()!=null && i<command.getItems().size();i++){
            Commodity commodity=CommonCache.COMMODITY_CONVERT.get(command.getItems().get(i).getProduct());
            Map<String,Object> discountMap=commodity.discount(command.getItems().get(i).getAmount());
            DiscountItemRepresentation discountItemRepresentation=(DiscountItemRepresentation)discountMap.get("discountItemRepresentation");
            if(discountItemRepresentation.getDiscount()!=null && discountItemRepresentation.getDiscount().intValue()>0){
                discountItemList.add((DiscountItemRepresentation)discountMap.get("discountItemRepresentation"));
            }
            if(discountMap.get("discountDesc")!=null){
                discountCards.add(discountMap.get("discountDesc").toString());
            }
        }
        discountItemMap.put("discountItemList",discountItemList);
        discountItemMap.put("discountCards",discountCards);
        return discountItemMap;
    }
    BigDecimal getTotalDiscountPrice(List<DiscountItemRepresentation> discountItemList){
        BigDecimal totalDiscountPrice=BigDecimal.ZERO;
        for(int i=0;discountItemList!=null && i<discountItemList.size();i++){
            totalDiscountPrice=totalDiscountPrice.add(discountItemList.get(i).getDiscount());
        }
        return totalDiscountPrice;
    }
    BigDecimal getTotalPrice(OrderCommand command){
        BigDecimal totalPrice=BigDecimal.ZERO;
        for(int i=0;command.getItems()!=null && i<command.getItems().size();i++){
            Commodity commodity=CommonCache.COMMODITY_CONVERT.get(command.getItems().get(i).getProduct());
            totalPrice=totalPrice.add(commodity.getCommodityPrice().multiply(command.getItems().get(i).getAmount()));
        }
        return totalPrice;
    }
    List<OrderItemRepresentation> getOrderItemList(OrderCommand command){
        List<OrderItemRepresentation> orderItemList=new ArrayList<OrderItemRepresentation>();
        for(int i=0;command.getItems()!=null && i<command.getItems().size();i++){
            Commodity commodity=CommonCache.COMMODITY_CONVERT.get(command.getItems().get(i).getProduct());
            BigDecimal subTotal=commodity.getCommodityPrice().multiply(command.getItems().get(i).getAmount());
            OrderItemRepresentation orderItemRepresentation=new OrderItemRepresentation(commodity.getCommodityNo()
            ,commodity.getCommodityName(),commodity.getCommodityPrice(),command.getItems().get(i).getAmount(), subTotal);
            orderItemList.add(orderItemRepresentation);
        }
        return orderItemList;
    }
}
