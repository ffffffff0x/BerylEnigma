package ffffffff0x.beryenigma.App.Controller.Tools.Practical.MoneyConvert;

import java.math.BigDecimal;

/**
 * @author: RyuZUSUNC
 * @create: 2021-03-01 14:11
 **/

public class Practical_MoneyConvert {

    private static final String[] CN_NUMBER = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static final String[] CN_UNIT  = { "分", "角", "圆","拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾", "佰", "仟", "顺" };

    //额外附加字符
    private static final String CN_NEGATIVE = "负";
    private static final String CN_FULL = "整";
    private static final String CN_ZERO_FULL = "零圆整";
    private static final int PERCISION = 2;

    public static String convert(BigDecimal moneyValue){
        //存储最终答案的StringBulider
        StringBuilder Result = new StringBuilder();

        //当此 BigDecimal 的值为负、零或正时，返回 -1、0 或 1。
        int signum = moneyValue.signum();

        //若输入为0，输出零圆整；
        if(signum == 0) {
            return CN_ZERO_FULL;
        }

        //对金额进行四舍五入转化为long类型的整数；先将数的小数点向右移两位，然后在四舍五入，取绝对值，最后将它转换为长整型；
        long number = moneyValue.movePointRight(PERCISION).setScale(0, BigDecimal.ROUND_HALF_UP).abs().longValue();
        //记录数字的个数；
        int numIndex = 0;
        boolean getZero = false;

        //得到小数部分（小数点后两位）；
        long scale = number % 100;
        //若小数部分为"00"时的情况；在最后追加特殊字符：整
        if(scale == 0) {
            numIndex += 2;
            getZero = true;
            // 从number去掉为0数；
            number /= 100;
            Result.append(CN_FULL);
            //若小数部分为"*0"时的情况；
        } else if(scale % 10 == 0){
            numIndex += 1;
            getZero = true;
            // 从number去掉为0数；
            number /= 10;
        }

        //排除上述两种小数部分的特殊情况，则对小数和整数的处理就是一样地了。
        while(true) {
            //循环结束条件；
            if (number <= 0) {
                break;
            }

            //每次通过取余来得到最后一位数；
            int numUnit = (int) (number % 10);
            if (numUnit != 0) {
                //先添加单位
                Result.insert(0, CN_UNIT[numIndex]);
                //在添加根据数字值来对应数组中的中文表述；
                Result.insert(0, CN_NUMBER[numUnit]);
                //表明当前数不是0；
                getZero = false;
            } else {
                //意思是它的上一次的数不是零，那么打印出零；
                if (!getZero) {
                    Result.insert(0, CN_NUMBER[numUnit]);
                }
                //若角分位为零，那么打印零；
                if (numIndex == 2) {
                    if (number > 0) {
                        Result.insert(0, CN_UNIT[numIndex]);
                    }
                    //第一个条件是为了每隔4位，打印“圆，万，亿”；第二个条件是为了避免出现如图3的情况；
                } else if ((numIndex - 2) % 4 == 0 && number % 1000 != 0) {
                    Result.insert(0, CN_UNIT[numIndex]);
                }
                //将其置为true,那么如果下一位还是0,也不会再打印一遍'零'；避免出现图2的情况；
                getZero = true;
            }
            // 从number每次都去掉最后一个数
            number = number / 10;
            numIndex++;
        }

        // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
        if(signum == -1) {
            Result.insert(0, CN_NEGATIVE);
        }

        return Result.toString();
    }
}
