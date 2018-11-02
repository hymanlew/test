package hyman.demo;

import java.io.OutputStream;
import java.math.BigDecimal;

/**
 * java BigDecimal 实现精确加减乘除运算，例如与银行资金相关的金额运算。
 * 不能用 double 直接计算，会出现精度损失的问题，出现一串 9 的问题。
 *
 * 由于Java的简单类型不能够精确的对浮点数进行运算。
 */
public class NumberDemo {

    public static void main(String[] args) {
        Double d = 2.2;
        BigDecimal big = BigDecimal.valueOf(d);
        System.out.println(big);

        // 不可以直接放入 double 类型，精度会损失，必须要转换为字符串才可以。或者使用 valueOf
        //big = new BigDecimal(d);
        big = new BigDecimal(d.toString());
        System.out.println(big);

        String d1 = "3.1";
        BigDecimal big1 = new BigDecimal(d1);
        System.out.println(big1);

        // 输出 double 类型数据，或者存入数据库。
        System.out.println("=========== 加，减，乘，除 ============");

        System.out.println(big1.add(big).doubleValue());

        System.out.println(big1.subtract(big).doubleValue());

        // 提供精确的乘法运算。
        System.out.println(big1.multiply(big).doubleValue());

        // 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到小数点以后10位，以后的数字四舍五入。
        System.out.println(div(3.3,2.2,5));
    }


    // int scale，是表示精度，指小数点后几位
    public static Double div(double v1,double v2,int scale){

        if(scale<0){
            throw new IllegalArgumentException("精度参数小于 0！");
        }
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);

        /**
         * ROUND_HALF_UP : 向“最接近的”数字舍入，如果遇到.5的情况时往上近似,例: 1.5 -> 2。即四舍五入模式。
         * ROUND_HALF_DOWN : 向“最接近的”数字舍入，是五舍六入模式。
         *
         * ROUND_UP ：在丢弃非零部分之前始终增加数字(始终对非零舍弃部分前面的数字加1)，此舍入模式始终不会减少计算值的大小。
         * ROUND_DOWN ：在丢弃某部分之前始终不增加数字(从不对舍弃部分前面的数字加1，即截短)，此舍入模式始终不会增加计算值的大小。
         *
         * ROUND_CEILING ：接近正无穷大的舍入模式。如果为正，则舍入与 ROUND_UP 相同。如果为负，则舍入与 ROUND_DOWN 相同。此舍入模式始终不会减少计算值。
         * ROUND_FLOOR ：接近负无穷大的舍入模式。如果为正，则舍入与 ROUND_DOWN 相同。如果为负，则舍入行为与 ROUND_UP 相同。此舍入模式始终不会增加计算值。
         *
         *
         * ROUND_HALF_EVEN ：向“最接近的”数字舍入，如果舍弃部分左边的数字为奇数，则舍入与 ROUND_HALF_UP 相同。如果为偶数，
         *              则舍入行为与 ROUND_HALF_DOWN 相同。在重复进行一系列计算时，此舍入模式可以将累加错误减到最小。
         *              此舍入模式也称为“银行家舍入法”，主要在美国使用。四舍六入，五分两种情况。
         *              如果前一位为奇数，则入位，否则舍去。   1.15>1.2 1.25>1.2
         *
         *
         * ROUND_UNNECESSARY ：断言请求的操作具有精确的结果，因此不需要舍入。如果对获得精确结果的操作指定此舍入模式，则抛出ArithmeticException。
         *
         * 互联网金融行业，所有在进行计算的时候尽量使用 ROUND_HALF_EVEN（银行家舍入法）。
         */
        return b2.divide(b1,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
