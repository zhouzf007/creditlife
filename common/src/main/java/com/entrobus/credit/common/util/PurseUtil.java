package com.entrobus.credit.common.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * 钱包工具类
 * Created by laotou on 2016/6/3.
 */
public class PurseUtil {
    /**
     * 将百分号转换为小数
     * @param rate 百分率 以 % 结尾
     * @return
     */
    public static BigDecimal percentSignConvert(String  rate){
        rate = rate.replace("%", "");
        return new BigDecimal(rate).divide(new BigDecimal(100));
    }
    /**
     * 将百分号转换为小数
     * @param rate 百分率 以 % 结尾
     * @return
     */
    public static double percentSignConvertDouble(String  rate){
        return percentSignConvert(rate).doubleValue();
    }

    /**
     * 折扣金额计算
     * @param oldPrice 原金额
     * @param rate 折扣率，注意如果不以“%”结束，需要转为小数（如八折可以传入： "0.8" 或 "80%"都可以）
     * @return 返回 oldPrice 和 rate 的乘积
     */
    public static String discount(String oldPrice ,String  rate){
        return discount(new BigDecimal(oldPrice),rate).toPlainString();
    }
    /**
     * 折扣金额计算
     * @param oldPrice 原金额
     * @param rate 折扣率，注意如果不以“%”结束，需要转为小数（如八折可以传入： "0.8" 或 "80%"都可以）
     * @param scale 保留小数位数
     * @return 返回 oldPrice 和 rate 的乘积
     */
    public static String discount(String oldPrice ,String  rate,int scale){
        return discount(new BigDecimal(oldPrice),rate,scale).toPlainString();
    }
    /**
     * 折扣金额计算
     * @param oldPrice 原金额
     * @param rate 折扣率，注意如果不以“%”结束，需要转为小数（如八折可以传入： "0.8" 或 "80%"都可以）
     * @return 返回 oldPrice 和 rate 的乘积
     */
    public static double discount(double oldPrice ,String  rate){
        return discount(new BigDecimal(oldPrice),rate).doubleValue();
    }
    /**
     * 折扣金额计算
     * @param oldPrice 原金额
     * @param rate 折扣率，注意如果不以“%”结束，需要转为小数（如八折可以传入： "0.8" 或 "80%"都可以）
     * @param scale 保留小数位数
     * @return 返回 oldPrice 和 rate 的乘积
     */
    public static double discount(double oldPrice ,String  rate,int scale){
        return discount(new BigDecimal(oldPrice),rate,scale).doubleValue();
    }
    /**
     * 折扣金额计算
     * @param oldPrice 原金额
     * @param rate 折扣率，注意如果不以“%”结束，需要转为小数（如八折可以传入： "0.8" 或 "80%"都可以）
     * @return 返回 oldPrice 和 rate 的乘积
     */
    public static long discount(long oldPrice ,String  rate){
        return discount(new BigDecimal(oldPrice),rate,0).longValue();
    }
    /**
     * 折扣金额计算
     * @param oldPrice 原金额
     * @param rate 折扣率，注意如果不以“%”结束，需要转为小数（如八折可以传入： "0.8" 或 "80%"都可以）
     * @return 返回 oldPrice 和 rate 的乘积
     */
    public static BigDecimal discount(BigDecimal oldPrice ,String  rate,int scale){
        return discount(oldPrice,rate).setScale(scale,BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 折扣金额计算
     * @param oldPrice 原金额
     * @param rate 折扣率，注意如果不以“%”结束，需要转为小数（如八折可以传入： "0.8" 或 "80%"都可以）
     * @return 返回 oldPrice 和 rate 的乘积
     */
    public static BigDecimal discount(BigDecimal oldPrice ,String  rate){
        if (rate == null || rate.length() == 0){
           return oldPrice;
        }else if (rate.endsWith("%") ){
            return oldPrice.multiply(percentSignConvert(rate));
        }
        return oldPrice.multiply(new BigDecimal(rate));
    }
    /**
     * 折扣金额计算
     * @param oldPrice 原金额
     * @param rate 折扣率，注意需要转为小数（如八折=0.8）
     * @return 返回 oldPrice 和 rate 的乘积
     */
    public static String discount(String oldPrice ,double  rate){
        return discount(new BigDecimal(oldPrice),rate).toPlainString();
    }
    /**
     * 折扣金额计算
     * @param oldPrice 原金额
     * @param rate 折扣率，注意需要转为小数（如八折=0.8）
     * @return 返回 oldPrice 和 rate 的乘积
     */
    public static String discount(String oldPrice ,double  rate,int scale){
        return discount(new BigDecimal(oldPrice),rate,scale).toPlainString();
    }
    /**
     * 折扣金额计算
     * @param oldPrice 原金额
     * @param rate 折扣率，注意需要转为小数（如八折=0.8）
     * @return 返回 oldPrice 和 rate 的乘积
     */
    public static long discount(long oldPrice ,double  rate){
        return discount(new BigDecimal(oldPrice),rate).longValue();
    }
    /**
     * 折扣金额计算
     * @param oldPrice 原金额
     * @param rate 折扣率，注意需要转为小数（如八折=0.8）
     * @return 返回 oldPrice 和 rate 的乘积
     */
    public static double discount(double oldPrice ,double  rate){
        return discount(new BigDecimal(oldPrice),rate).doubleValue();
    }
    /**
     * 折扣金额计算
     * @param oldPrice 原金额
     * @param rate 折扣率，注意需要转为小数（如八折=0.8）
     * @return 返回 oldPrice 和 rate 的乘积
     */
    public static double discount(double oldPrice ,double  rate,int scale){
        return discount(new BigDecimal(oldPrice),rate,scale).doubleValue();
    }
    /**
     * 折扣金额计算
     * @param oldPrice 原金额
     * @param rate 折扣率，注意需要转为小数（如八折=0.8）
     * @return 返回 oldPrice 和 rate 的乘积
     */
    public static BigDecimal discount(BigDecimal oldPrice ,double  rate){
        return oldPrice.multiply(new BigDecimal(rate));
    }
    /**
     * 折扣金额计算
     * @param oldPrice 原金额
     * @param rate 折扣率，注意需要转为小数（如八折=0.8）
     * @return 返回 oldPrice 和 rate 的乘积
     */
    public static BigDecimal discount(BigDecimal oldPrice ,double  rate,int scale){
        return discount(oldPrice,rate).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 根据单价和数量求总金额
     * @param unitPrice 单价
     * @param quantity 数量
     * @return 返回 unitPrice 和 quantity 等乘积
     */
    public static String amount(String unitPrice ,int quantity){
        return amount(new BigDecimal(unitPrice), quantity).toPlainString();
    }
    /**
     * 根据单价和数量求总金额
     * @param unitPrice 单价
     * @param quantity 数量
     * @return 返回 unitPrice 和 quantity 等乘积
     */
    public static long amount(long unitPrice ,int quantity){
        return amount(new BigDecimal(unitPrice), quantity).longValue();
    }
    /**
     * 根据单价和数量求总金额
     * @param unitPrice 单价
     * @param quantity 数量
     * @return 返回 unitPrice 和 quantity 等乘积
     */
    public static double amount(double unitPrice ,int quantity){
        return amount(new BigDecimal(unitPrice),quantity).doubleValue();
    }
    /**
     * 根据单价和数量求总金额
     * @param unitPrice 单价
     * @param quantity 数量
     * @return 返回 unitPrice 和 quantity 等乘积
     */
    public static BigDecimal amount(BigDecimal unitPrice ,int quantity){
        return unitPrice.multiply(new BigDecimal(quantity));
    }

    /**
     * 金额减法法运算，amt1,amt2单位需一致，返回以参数一致单位的字符串金额
     * @param amt1
     * @param amt2
     * @return 返回 amt1减去amt2(所有)
     */
    public static String subtract(String amt1 ,String... amt2){
        BigDecimal bd = new BigDecimal(amt1);
        for (String s : amt2){
            bd = bd.subtract(new BigDecimal(s));
        }
        return bd.toPlainString();
    }
    /**
     * 金额减法法运算，amt1,amt2单位需一致，返回以参数一致单位的long金额
     * @param amt1
     * @param amt2
     * @return 返回 amt1减去amt2(所有)
     */
    public static long subtract(long amt1 ,long... amt2){
        BigDecimal bd = new BigDecimal(amt1);
        for (long s : amt2){
            bd = bd.subtract(new BigDecimal(s));
        }
        return bd.longValue();
    }
    /**
     * 金额减法法运算，amt1,amt2单位需一致，返回以参数一致单位的double金额
     * @param amt1
     * @param amt2
     * @return 返回 amt1减去amt2(所有)
     */
    public static double subtract(double amt1 ,double... amt2){
        BigDecimal bd = new BigDecimal(amt1);
        for (double s : amt2){
            bd = bd.subtract(new BigDecimal(s));
        }
        return bd.doubleValue();
    }
    /**
     * 金额减法法运算，amt1,amt2单位需一致，返回以参数一致单位的BigDecimal金额
     * @param amt1
     * @param amt2
     * @return 返回 amt1减去amt2(所有)
     */
    public static BigDecimal subtract(BigDecimal amt1 ,BigDecimal... amt2){
        BigDecimal bd = amt1;
        for (BigDecimal s : amt2){
            bd = bd.subtract(s);
        }
        return bd;
    }
    /**
     * 金额加法运算，amt1,amt2单位需一致，返回以参数一致单位的字符串金额
     * @param amt1
     * @param amt2
     * @return
     */
    public static String add(String amt1 ,String... amt2){
        BigDecimal bd = new BigDecimal(amt1);
        for (String s : amt2){
            bd = bd.add(new BigDecimal(s));
        }
        return bd.toPlainString();
    }
    /**
     * 金额加法运算，amt1,amt2单位需一致，返回以参数一致单位的长整型金额
     * @param amt1
     * @param amt2
     * @return
     */
    public static long add(long amt1 ,long... amt2){
        BigDecimal bd = new BigDecimal(amt1);
        for (long s : amt2){
            bd = bd.add(new BigDecimal(s));
        }
        return bd.longValue();
    }
    /**
     * 金额加法运算，amt1,amt2单位需一致，返回以参数一致单位的double金额
     * @param amt1
     * @param amt2
     * @return
     */
    public static double add(double amt1 ,double... amt2){
        BigDecimal bd = new BigDecimal(amt1);
        for (double s : amt2){
            bd = bd.add(new BigDecimal(s));
        }
        return bd.doubleValue();
    }
    /**
     * 金额加法运算，amt1,amt2单位需一致，返回以参数一致单位的BigDecimal金额
     * @param amt1
     * @param amt2
     * @return
     */
    public static BigDecimal add(BigDecimal amt1 ,BigDecimal... amt2){
        BigDecimal bd = amt1;
        for (BigDecimal s : amt2){
            bd = bd.add(s);
        }
        return bd;
    }
    /**
     * 比较字符串金额大小，金额单位必须一致
     * @param amt1
     * @param amt2
     * @return amt1大于amt2则返回1，相等返回0，小于返回-1
     */
    public static int compareTo(String amt1,String amt2){
        return new BigDecimal(amt1).compareTo(new BigDecimal(amt2));
    }
    /**
     * 比较长整型金额大小，金额单位必须一致
     * @param amt1
     * @param amt2
     * @return amt1大于amt2则返回1，相等返回0，小于返回-1
     */
    public static int compareTo(long amt1,long amt2){
        return new BigDecimal(amt1).compareTo(new BigDecimal(amt2));
    }
    /**
     * 比较double金额大小，金额单位必须一致
     * @param amt1
     * @param amt2
     * @return amt1大于amt2则返回1，相等返回0，小于返回-1
     */
    public static int compareTo(double amt1,double amt2){
        return new BigDecimal(amt1).compareTo(new BigDecimal(amt2));
    }
    /**
     * 比较BigDecimal金额大小，金额单位必须一致
     * @param amt1
     * @param amt2
     * @return amt1大于amt2则返回1，相等返回0，小于返回-1
     */
    public static int compareTo(BigDecimal amt1,BigDecimal amt2){
        return amt1.compareTo(amt2);
    }

    /**
     * 将以元为单位的字符串金额转换为
     * 以分为单位的长整型
     *
     * @param amtYuan 以元为单位的字符串金额
     * @return
     */
    public static long toFenLong(String amtYuan) {
        return  toFenBigDecimal(amtYuan).longValue();
    }
    /**
     * 将以元为单位的double金额转换为
     * 以分为单位的长整型
     *
     * @param amtYuan 以元为单位的double金额
     * @return
     */
    public static long toFenLong(double amtYuan) {
        return  toFenBigDecimal(amtYuan).longValue();
    }
    /**
     * 将以元为单位的BigDecimal金额转换为
     * 以分为单位的长整型
     *
     * @param amtYuan 以元为单位的BigDecimal金额
     * @return
     */
    public static long toFenLong(BigDecimal amtYuan) {
        return  toFenBigDecimal(amtYuan).longValue();
    }
    /**
     * 将以元为单位的字符串金额转换为
     * 以分为单位的字符串
     *
     * @param amtYuan 以元为单位的字符串金额
     * @return
     */
    public static String toFenString(String amtYuan) {
        return  toFenBigDecimal(amtYuan).toPlainString();
    }
    /**
     * 将以元为单位的double金额转换为
     * 以分为单位的字符串
     *
     * @param amtYuan 以元为单位的double金额
     * @return
     */
    public static String toFenString(double amtYuan) {
        return  toFenBigDecimal(amtYuan).toPlainString();
    }
    /**
     * 将以元为单位的BigDecimal金额转换为
     * 以分为单位的字符串
     *
     * @param amtYuan 以元为单位的BigDecimal金额
     * @return
     */
    public static String toFenString(BigDecimal amtYuan) {
        return  toFenBigDecimal(amtYuan).toPlainString();
    }
    /**
     * 将以分为单位的长整型金额转换为
     * 以分为单位的字符串
     *
     * @param amtFen 以分为单位的长整型金额
     * @return
     */
    public static String toFenString(long amtFen) {
        return String.valueOf(amtFen);
    }
    /**
     * 将以元为单位的字符串金额转换为
     * 以分为单位的BigDecimal
     *
     * @param amtYuan 以元为单位的字符串金额
     * @return
     */
    public static BigDecimal toFenBigDecimal(String amtYuan) {
        return  new BigDecimal(amtYuan).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 将以元为单位的double金额转换为
     * 以分为单位的BigDecimal
     *
     * @param amtYuan 以元为单位的double金额
     * @return
     */
    public static BigDecimal toFenBigDecimal(double amtYuan) {
        return  new BigDecimal(amtYuan).multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 将以元为单位的BigDecimal金额转换为
     * 以分为单位的BigDecimal
     *
     * @param amtYuan 以元为单位的double金额
     * @return
     */
    public static BigDecimal toFenBigDecimal(BigDecimal amtYuan) {
        return  amtYuan.multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 将以分为单位的长整型金额转换为
     * 以元为单位，最多保留两位小数的字符串，并去掉前后多余的0
     *
     * @param amtFen 分为单位的长整型金额
     * @return
     */
    public static String toYuanStringFormat(long amtFen) {
        return format(toYuanBigDecimal(amtFen));//去掉前后多余的0
    }
    /**
     * 将以分为单位的长整型金额转换为
     * 以元为单位，保留两位小数的字符串
     *
     * @param amtFen 分为单位的长整型金额
     * @return
     */
    public static String toYuanString(long amtFen) {
        return toYuanBigDecimal(amtFen).toPlainString();
    }
    /**
     * 将以分为单位的长整型金额转换为
     * 以元为单位，保留指定位小数的字符串
     *
     * @param amtFen 分为单位的长整型金额
     * @param scale 保留小数位数
     * @return
     */
    public static String toYuanString(long amtFen,int scale) {
        return toYuanBigDecimal(amtFen,scale).toPlainString();
    }
    /**
     * 将以分为单位的字符串金额转换为
     * 以元为单位，保留两位小数的字符串
     *
     * @param amtFen 分为单位的字符串金额
     * @return
     */
    public static String toYuanString(String  amtFen) {
        return new BigDecimal(amtFen).divide(new BigDecimal(100),2,  BigDecimal.ROUND_HALF_UP).toPlainString();
    }
    /**
     * 将以分为单位的字符串金额转换为
     * 以元为单位，保留两位小数的字符串
     *
     * @param amtFen 分为单位的字符串金额
     * @return
     */
    public static String toYuanStringFormat(String  amtFen) {
        return format(new BigDecimal(amtFen).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP));
    }
    /**
     * 将以分为单位的字符串金额转换为
     * 以元为单位，保留指定位小数的字符串
     *
     * @param amtFen 分为单位的字符串金额
     * @param scale 保留小数位数
     * @return
     */
    public static String toYuanString(String  amtFen,int scale) {
        return new BigDecimal(amtFen).divide(new BigDecimal(100), scale, BigDecimal.ROUND_HALF_UP).toPlainString();
    }
    /**
     * 将以元为单位的BigDecimal型金额转换为
     * 以元为单位，保留两位小数的字符串
     *
     * @param amtYuan 元为单位的BigDecimal型金额
     * @return
     */
    public static String toYuanString(BigDecimal amtYuan) {
        return amtYuan.setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString();
//        return amtYuan.toPlainString();
    }
    /**
     * 将以元为单位的BigDecimal型金额转换为
     * 以元为单位，保留指定位小数的字符串
     *
     * @param amtYuan 元为单位的BigDecimal型金额
     * @param scale 保留小数位数
     * @return
     */
    public static String toYuanString(BigDecimal amtYuan,int scale) {
        return amtYuan.setScale(scale,BigDecimal.ROUND_HALF_UP).toPlainString();
    }
    /**
     * 将以元为单位的double金额转换为
     * 以元为单位，最多保留两位小数的字符串，并去除多余的0
     *
     * @param amtYuan 以元为单位的double金额
     * @return
     */
    public static String toYuanStringFormat(double amtYuan) {
        return format(toYuanBigDecimal(amtYuan, 2));
    }
    /**
     * 将以元为单位的double金额转换为
     * 以元为单位，保留两位小数的字符串
     *
     * @param amtYuan 以元为单位的double金额
     * @return
     */
    public static String toYuanString(double amtYuan) {
        return toYuanBigDecimal(amtYuan,2).toPlainString();
    }
    /**
     * 将以元为单位的double金额转换为
     * 以元为单位，保留指定位小数的字符串
     *
     * @param amtYuan 以元为单位的double金额
     * @param scale 保留小数位数
     * @return
     */
    public static String toYuanString(double amtYuan,int scale) {
        return toYuanBigDecimal(amtYuan,scale).toPlainString();
    }
    /**
     * 将以分为单位的长整型金额转换为
     * 以元为单位的Double
     *
     * @param amtFen 以分为单位的长整型金额
     * @return
     */
    public static double toYuanDouble(long amtFen) {
        return toYuanBigDecimal(amtFen,2).doubleValue();
    }
    /**
     * 将以元为单位的字符串金额转换为
     * 以元为单位的Double
     *
     * @param amtYuan 以元为单位的字符串金额
     * @return
     */
    public static double toYuanDouble(String amtYuan) {
        return toYuanBigDecimal(amtYuan).doubleValue();
    }
    /**
     * 将以元为单位的BigDecimal金额转换为
     * 以元为单位的Double
     *
     * @param amtYuan 以元为单位的BigDecimal金额
     * @return
     */
    public static double toYuanDouble(BigDecimal amtYuan) {
        return amtYuan.doubleValue();
    }
    /**
     * 将以分为单位的长整型金额转换为
     * 以元为单位,保留两位小数的BigDecimal对象
     *
     * @param amtFen 以分为单位的长整型金额
     * @return
     */
    public static BigDecimal toYuanBigDecimal(long amtFen) {
        return new BigDecimal(amtFen).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 将以分为单位的长整型金额转换为
     * 以元为单位,保留指定位小数的BigDecimal对象
     *
     * @param amtFen 以分为单位的长整型金额
     * @param scale 保留小数位数
     * @return
     */
    public static BigDecimal toYuanBigDecimal(long amtFen,int scale) {
        return new BigDecimal(amtFen).divide(new BigDecimal(100), scale, BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 将以元为单位的字符串金额转换为
     * 保留两位小数，以元为单位的BigDecimal
     *
     * @param amtYuan 以元为单位的字符串金额
     * @return
     */
    public static BigDecimal toYuanBigDecimal(String amtYuan) {
        return new BigDecimal(amtYuan).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 将以元为单位的字符串金额转换为
     * 保留指定位小数，以元为单位的BigDecimal
     *
     * @param amtYuan 以元为单位的字符串金额。
     * @param scale 保留小数位数
     * @return
     */
    public static BigDecimal toYuanBigDecimal(String amtYuan,int scale) {
        return new BigDecimal(amtYuan).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 将以元为单位的double金额转换为
     * 保留两位小数，以元为单位的BigDecimal
     *
     * @param amtYuan 以元为单位的double金额
     * @return
     */
    public static BigDecimal toYuanBigDecimal(double amtYuan) {
        return new BigDecimal(amtYuan).setScale(2, BigDecimal.ROUND_HALF_UP);
//        return new BigDecimal(amtYuan);
    }
    /**
     * 将以元为单位的double金额转换为
     * 保留两位小数，以元为单位的BigDecimal
     *
     * @param amtYuan 以元为单位的double金额
     * @param scale 保留小数位数
     * @return
     */
    public static BigDecimal toYuanBigDecimal(double amtYuan,int scale) {
        return new BigDecimal(amtYuan).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 将以分为单位的BigDecimal金额转换为
     * 保留 指定 位小数，以元为单位的BigDecimal
     *
     * @param amtFen 以分为单位的BigDecimal金额
     * @param scale 保留小数位数
     * @return
     */
    public static BigDecimal toYuanBigDecimal(BigDecimal amtFen,int scale) {
        return amtFen.divide(new BigDecimal(100),scale,BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 将以分为单位的BigDecimal金额转换为
     * 保留两位小数，以元为单位的BigDecimal
     *
     * @param amtFen 以分为单位的BigDecimal金额
     * @return
     */
    public static BigDecimal toYuanBigDecimal(BigDecimal amtFen) {
        return amtFen.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 格式化，去除多余的0
     * @param bigDecimal
     * @return
     */
    public static String format(BigDecimal bigDecimal){
        return NumberFormat.getInstance().format(bigDecimal);
    }

    public static void main(String[] args) {
//        System.out.println(new BigDecimal("0.01").setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println("toYuanString:"+ toYuanString(160));
        System.out.println("toYuanString:"+ toYuanString(1.6));
        System.out.println("toYuanString:"+ toYuanString(new BigDecimal(1.6),2));
        System.out.println("toFenLong:"+ toFenLong("1.6"));
        System.out.println("toFenLong:"+ toFenLong(1.6));
        System.out.println("toFenLong:"+ toFenLong(new BigDecimal(1.6)));
        System.out.println("toYuanBigDecimal:"+ toYuanBigDecimal(160));
        System.out.println("toYuanBigDecimal:"+ toYuanBigDecimal("1.6"));
        System.out.println("toYuanBigDecimal:"+ toYuanBigDecimal(1.6));
        System.out.println("toYuanBigDecimal:"+ toYuanBigDecimal(new BigDecimal(160)));
        System.out.println("toYuanDouble:"+ toYuanDouble("1.6"));
        System.out.println("toYuanDouble:"+ toYuanDouble(160));
        System.out.println("toYuanDouble:"+ toYuanDouble(new BigDecimal(1.60)));
        System.out.println("toFenString:"+ toFenString("1.6"));
        System.out.println("toFenString:"+ toFenString(1.6));
        System.out.println("toFenString:"+ toFenString(160));
        System.out.println("toFenString:"+ toFenString(new BigDecimal(1.60)));
//
//        System.out.println("subtract:"+ subtract("5", "2", "1"));
//        System.out.println("subtract:"+ subtract("5", "2","1"));
//        System.out.println("subtract:"+ subtract(5, 2, 1));
//        System.out.println("subtract:"+ subtract(5.1, 2.2,1.3));
//        System.out.println("subtract:"+ subtract(new BigDecimal(5), new BigDecimal(2),new BigDecimal(1)));
//        System.out.println("add:"+ add("5", "-"+2.00, "1"));
//        System.out.println("add:"+ add(5, 2, 1));
//        System.out.println("add:"+ add(5.1, 2.2,1.3));
//        System.out.println("add:"+ add(new BigDecimal(5), new BigDecimal(2),new BigDecimal(1)));
//
//        System.out.println("compareTo:"+ compareTo("-0.01", "0.1"));
//        System.out.println("compareTo:"+ compareTo(5, 2));
//        System.out.println("compareTo:" + compareTo(5.1, 2.2));
//        System.out.println("compareTo:" + compareTo(new BigDecimal(1), new BigDecimal(5)));
//        System.out.println("add:"+ add("5", "2", "1"));
        System.out.println(discount(19555340L,"41%"));
        System.out.println(toYuanDouble(16530L));
        System.out.println("NumberFormat;" + toYuanStringFormat(00000000000000000001L));
        System.out.println("NumberFormat;" + format(new BigDecimal("0000000000000000000000000001")));
        System.out.println(new BigDecimal("1.600").toPlainString());

//        NumberFormat.getInstance().format("0.0200");
    }
}
