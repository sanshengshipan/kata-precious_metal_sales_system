package com.coding.sales.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 功能描述：关于日期时间类的工具
 *
 * @author 张兴帅
 * @version v1.0
 * @date 2019-07-02 15:51
 **/
public class DateNbUtils {

    /**
     * 日期格式
     */
    private static final SimpleDateFormat COMPACT_DATE_TIME_FORMATER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 日期将以：2019-03-19 19:05:30输出
     *
     */
    public static Date formatCompactDateTime(String date) {
        Date createTime=null;
        if (date == null) {
            return null;
        }
        try{
            createTime=COMPACT_DATE_TIME_FORMATER.parse(date);
        }catch (Exception e){

        }
        return createTime;
    }

}
