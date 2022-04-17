package hyman.utils2;

import hyman.utils.Convert;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串格式化
 */
public class StringFormatter {

    public static final char SEPARATOR = '_';
    public static final String EMPTY_JSON = "{}";
    public static final char C_BACKSLASH = '\\';
    public static final char C_DELIM_START = '{';
    public static final char C_DELIM_END = '}';

    /**
     * 格式化字符串<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     *
     * @param strPattern 字符串模板
     * @param argArray   参数列表
     * @return 结果
     */
    public static String format(final String strPattern, final Object... argArray) {
        if (StringUtils.isEmpty(strPattern) || StringConversUtil.isEmpty(argArray)) {
            return strPattern;
        }
        final int strPatternLength = strPattern.length();

        // 初始化定义好的长度以获得更好的性能
        StringBuilder sbuf = new StringBuilder(strPatternLength + 50);

        int handledPosition = 0;
        int delimIndex;// 占位符所在位置
        for (int argIndex = 0; argIndex < argArray.length; argIndex++) {
            delimIndex = strPattern.indexOf(EMPTY_JSON, handledPosition);
            if (delimIndex == -1) {
                if (handledPosition == 0) {
                    return strPattern;
                } else { // 字符串模板剩余部分不再包含占位符，加入剩余部分后返回结果
                    sbuf.append(strPattern, handledPosition, strPatternLength);
                    return sbuf.toString();
                }
            } else {
                if (delimIndex > 0 && strPattern.charAt(delimIndex - 1) == C_BACKSLASH) {
                    if (delimIndex > 1 && strPattern.charAt(delimIndex - 2) == C_BACKSLASH) {
                        // 转义符之前还有一个转义符，占位符依旧有效
                        sbuf.append(strPattern, handledPosition, delimIndex - 1);
                        sbuf.append(Convert.utf8Str(argArray[argIndex]));
                        handledPosition = delimIndex + 2;
                    } else {
                        // 占位符被转义
                        argIndex--;
                        sbuf.append(strPattern, handledPosition, delimIndex - 1);
                        sbuf.append(C_DELIM_START);
                        handledPosition = delimIndex + 1;
                    }
                } else {
                    // 正常占位符
                    sbuf.append(strPattern, handledPosition, delimIndex);
                    sbuf.append(Convert.utf8Str(argArray[argIndex]));
                    handledPosition = delimIndex + 2;
                }
            }
        }
        // 加入最后一个占位符后所有的字符
        sbuf.append(strPattern, handledPosition, strPattern.length());

        return sbuf.toString();
    }


    /**
     * 字符串转set
     *
     * @param str 字符串
     * @param sep 分隔符
     * @return set集合
     */
    public static final Set<String> str2Set(String str, String sep) {
        return new HashSet<String>(str2List(str, sep, true, false));
    }

    /**
     * 字符串转list
     *
     * @param str         字符串
     * @param sep         分隔符
     * @param filterBlank 过滤纯空白
     * @param trim        去掉首尾空白
     * @return list集合
     */
    public static final List<String> str2List(String str, String sep, boolean filterBlank, boolean trim) {
        List<String> list = new ArrayList<String>();
        if (StringUtils.isEmpty(str)) {
            return list;
        }

        // 过滤空白字符串
        if (filterBlank && StringUtils.isBlank(str)) {
            return list;
        }
        String[] split = str.split(sep);
        for (String string : split) {
            if (filterBlank && StringUtils.isBlank(string)) {
                continue;
            }
            if (trim) {
                string = string.trim();
            }
            list.add(string);
        }

        return list;
    }

    /**
     * 下划线转驼峰命名
     */
    public static String toUnderScoreCase(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        // 前置字符是否大写
        boolean preCharIsUpperCase = true;
        // 当前字符是否大写
        boolean curreCharIsUpperCase = true;
        // 下一字符是否大写
        boolean nexteCharIsUpperCase = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i > 0) {
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            } else {
                preCharIsUpperCase = false;
            }

            curreCharIsUpperCase = Character.isUpperCase(c);

            if (i < (str.length() - 1)) {
                nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }

            if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
                sb.append(SEPARATOR);
            } else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase) {
                sb.append(SEPARATOR);
            }
            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) {
            for (String s : strs) {
                if (str.equalsIgnoreCase(StringConversUtil.trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。 例如：HELLO_WORLD->HelloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String convertToCamelCase(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母大写
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 首字母大写
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }

    /**
     * 驼峰式命名法 例如：user_name->userName
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        return (T) obj;
    }

    public static void main(String[] args) {
        List<Double> numberFromString = getNumberFromString("3-4级 微风  <7.9m/s");
        System.out.println();

    }

    /**
     * @param
     * @return 返回区间字符串
     * @Description 取整等分区间
     * @author liucheng
     */
    public static String[] sectionSplit(String start, String end) {
        start = startCal(start);

        String[] result = new String[10];
        int length = start.length();
        int point = start.indexOf(".");
        //传进来的小数位数有几位
        int pointLength = length - point - 1;
        String format = null;
        if (pointLength >= 2 && point != -1) {
            format = "#.00";
        } else if (pointLength == 1 && point != -1) {
            format = "#.0";
        } else {
            format = "#";
        }
        DecimalFormat df = new DecimalFormat(format);

        Double dis = (Double.valueOf(end) - Double.valueOf(start)) / 10;
        if (Double.valueOf(start).equals(0d)) {
            for (int i = 0; i < 10; i++) {
                String fm = df.format(Double.valueOf(start) + i * dis);
                if (fm.charAt(0) == '.') {
                    fm = "0" + fm;
                }
                result[i] = fm;

            }
            return result;
        }
        String rea = null;
        if (format.equals("#")) {
            rea = String.valueOf(Math.round(dis));
        } else {
            rea = df.format(dis);
            if (pointLength > 1) {
                rea = String.valueOf(new BigDecimal(rea).setScale(pointLength - 1, BigDecimal.ROUND_UP).doubleValue());
            }
        }
        Double start1 = Double.valueOf(df.format(Double.valueOf(start)));
        if (pointLength > 1) {
            start1 = new BigDecimal(start1).setScale(pointLength - 1, BigDecimal.ROUND_UP).doubleValue();
        }

        Double end1 = Double.valueOf(end);
        int k = 0;
        for (int i = 0; i < 10; i++) {
            if (start1 <= end1) {
                if (df.format(start1).indexOf('.') == 0) {
                    StringBuffer buffer = new StringBuffer(df.format(start1));
                    buffer.insert(0, "0");
                    result[i] = buffer.toString();
                } else {
                    result[i] = df.format(start1);
                }
                result[i] = result[i] + "-";
                start1 += Double.valueOf(rea);
                if (start1 <= end1 && i != 9) {
                    if (df.format(start1).indexOf('.') == 0) {
                        StringBuffer buffer = new StringBuffer(df.format(start1));
                        buffer.insert(0, "0");
                        result[i] = result[i] + buffer.toString();
                    } else {
                        result[i] = result[i] + df.format(start1);
                    }
                } else {
                    result[i] = result[i].replace("-", "+");
                }

            } else {
                if (k != 0) {
                    start1 += Double.valueOf(rea);
                }
                if (df.format(start1).indexOf('.') == 0) {
                    StringBuffer buffer = new StringBuffer(df.format(start1));
                    buffer.insert(0, "0");
                    result[i] = buffer.toString();
                }
                result[i] = df.format(start1) + "+";
                k++;
            }
        }
        for (int i = 0; i < result.length; i++) {
            int j = result[i].lastIndexOf("-");
            if (j != -1) {
                result[i] = result[i].substring(0, j);
            }
            result[i] = result[i].replace("+", "");
        }
        return result;
    }

    //处理start
    public static String startCal(String start) {
        if (Double.valueOf(start).equals(0d)) {
            return start;
        } else {
            while (start.charAt(start.length() - 1) == '0') {
                start = start.substring(0, start.length() - 1);
                startCal(start);
            }
            if (start.charAt(start.length() - 1) == '.') {
                start = start.substring(0, start.length() - 1);
                return start;
            } else {
                return start;
            }
        }

    }

    //提取字符串中的数字
    public static List<Double> getNumberFromString(String text) {
        List<Double> numbers = new ArrayList<>();
        String pattern = "([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher matcher = r.matcher(text);
        Set<String> matchStrs = new HashSet<>();
        while (matcher.find()) {
            //获取当前匹配的值
            matchStrs.add(matcher.group());
        }
        if (matchStrs.size() == 0) {
            return numbers;
        }
        for (String s : matchStrs) {
            try {
                double number = Double.parseDouble(s);
                numbers.add(number);
            } catch (Exception e) {
                continue;
            }
        }
        return numbers;
    }
}
