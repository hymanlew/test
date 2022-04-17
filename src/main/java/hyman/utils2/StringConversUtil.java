package hyman.utils2;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

public class StringConversUtil extends org.apache.commons.lang3.StringUtils {

    private static final String NULLSTR = "";
    private static final char SEPARATOR = '_';


    public static String toUnderline(String str) {

        //字符串首字母大小写转换
        //capitalize("china")); // China (首字母转大写)
        //uncapitalize("CHINA")); // cHINA (首字母转小写)
        String strValue = StringUtils.uncapitalize(str);
        char[] letters = strValue.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char letter : letters) {
            if (Character.isUpperCase(letter)) {
                sb.append("_" + letter + "");
            } else {
                sb.append(letter + "");
            }
        }
        return StringUtils.lowerCase(sb.toString());
    }

    public static String htmlToText(String str) {
        return HtmlUtils.htmlEscape(str);
    }

    /**
     * map转str
     *
     * @param map
     * @return
     */
    public static String getMapToString(Map<String, String> map) {
        Set<String> keySet = map.keySet();
        //将set集合转换为数组
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        //给数组排序(升序)
        Arrays.sort(keyArray);
        //因为String拼接效率会很低的，所以转用StringBuilder。博主会在这篇博文发后不久，会更新一篇String与StringBuilder开发时的抉择的博文。
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyArray.length; i++) {
            // 参数值为空，则不参与签名 这个方法trim()是去空格
            if (map.get(keyArray[i]).trim().length() > 0) {
                sb.append(keyArray[i]).append("=").append(map.get(keyArray[i]).trim());
            }
            if (i != keyArray.length - 1) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

    /**
     * 2018年10月24日更新
     * String转map
     *
     * @param str
     * @return
     */
    public static Map<String, String> getStringToMap(String str) {
        //判断str是否有值
        if (null == str || "".equals(str)) {
            return null;
        }
        //根据&截取
        String[] strings = str.split("&");
        //设置HashMap长度
        int mapLength = strings.length;
        //判断hashMap的长度是否是2的幂。
        if ((strings.length % 2) != 0) {
            mapLength = mapLength + 1;
        }

        Map<String, String> map = new HashMap<>(mapLength);
        //循环加入map集合
        for (int i = 0; i < strings.length; i++) {
            //截取一组字符串
            String[] strArray = strings[i].split("=");
            //strArray[0]为KEY  strArray[1]为值
            map.put(strArray[0], strArray[1]);
        }
        return map;
    }


    public static <T> T nvl(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    public static boolean isEmpty(Collection<?> coll) {
        return isNull(coll) || coll.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    public static boolean isEmpty(Object[] objects) {
        return isNull(objects) || objects.length == 0;
    }

    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(String str) {
        return isNull(str) || "".equals(str.trim());
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    public static boolean isArray(Object object) {
        return isNotNull(object) && object.getClass().isArray();
    }

    public static String trim(String str) {
        return str == null ? "" : str.trim();
    }

    public static String substring(final String str, int start) {
        if (str == null) {
            return "";
        } else {
            if (start < 0) {
                start += str.length();
            }

            if (start < 0) {
                start = 0;
            }

            return start > str.length() ? "" : str.substring(start);
        }
    }

    public static String substring(final String str, int start, int end) {
        if (str == null) {
            return "";
        } else {
            if (end < 0) {
                end += str.length();
            }

            if (start < 0) {
                start += str.length();
            }

            if (end > str.length()) {
                end = str.length();
            }

            if (start > end) {
                return "";
            } else {
                if (start < 0) {
                    start = 0;
                }

                if (end < 0) {
                    end = 0;
                }

                return str.substring(start, end);
            }
        }
    }

    public static String format(String template, Object... params) {
        return !isEmpty(params) && !isEmpty(template) ? StringFormatter.format(template, params) : template;
    }

    public static final Set<String> str2Set(String str, String sep) {
        return new HashSet(str2List(str, sep, true, false));
    }

    public static final List<String> str2List(String str, String sep, boolean filterBlank, boolean trim) {
        List<String> list = new ArrayList();
        if (isEmpty(str)) {
            return list;
        } else if (filterBlank && isBlank(str)) {
            return list;
        } else {
            String[] split = str.split(sep);
            String[] var6 = split;
            int var7 = split.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                String string = var6[var8];
                if (!filterBlank || !isBlank(string)) {
                    if (trim) {
                        string = string.trim();
                    }

                    list.add(string);
                }
            }

            return list;
        }
    }

    public static String toUnderScoreCase(String str) {
        if (str == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean preCharIsUpperCase = true;
            boolean curreCharIsUpperCase = true;
            boolean nexteCharIsUpperCase = true;

            for(int i = 0; i < str.length(); ++i) {
                char c = str.charAt(i);
                if (i > 0) {
                    preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
                } else {
                    preCharIsUpperCase = false;
                }

                curreCharIsUpperCase = Character.isUpperCase(c);
                if (i < str.length() - 1) {
                    nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
                }

                if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
                    sb.append('_');
                } else if (i != 0 && !preCharIsUpperCase && curreCharIsUpperCase) {
                    sb.append('_');
                }

                sb.append(Character.toLowerCase(c));
            }

            return sb.toString();
        }
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

    public static String convertToCamelCase(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && !name.isEmpty()) {
            if (!name.contains("_")) {
                return name.substring(0, 1).toUpperCase() + name.substring(1);
            } else {
                String[] camels = name.split("_");
                String[] var3 = camels;
                int var4 = camels.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    String camel = var3[var5];
                    if (!camel.isEmpty()) {
                        result.append(camel.substring(0, 1).toUpperCase());
                        result.append(camel.substring(1).toLowerCase());
                    }
                }

                return result.toString();
            }
        } else {
            return "";
        }
    }

    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        } else {
            s = s.toLowerCase();
            StringBuilder sb = new StringBuilder(s.length());
            boolean upperCase = false;

            for(int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                if (c == '_') {
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
    }

    public static <T> T cast(Object obj) {
        return (T) obj;
    }

    public static void main(String[] args) {
        sectionSplit("0.0", "1.0");
        int min = 0;
        int max = 1;
        double step = (double)((max - min) / 10);
        double[] range = new double[10];

        for(int i = 0; i < 10; ++i) {
            range[i] = (double)min + step;
        }

    }

    public static String[] sectionSplit(String start, String end) {
        start = startCal(start);
        String[] result = new String[10];
        int length = start.length();
        int point = start.indexOf(".");
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
        Double dis = (Double.valueOf(end) - Double.valueOf(start)) / 10.0D;
        if (Double.valueOf(start).equals(0.0D)) {
            for(int i = 0; i < 10; ++i) {
                String fm = df.format(Double.valueOf(start) + (double)i * dis);
                if (fm.charAt(0) == '.') {
                    fm = "0" + fm;
                }

                result[i] = fm;
            }

            return result;
        } else {
            String rea = null;
            if (format.equals("#")) {
                rea = String.valueOf(Math.round(dis));
            } else {
                rea = df.format(dis);
                if (pointLength > 1) {
                    rea = String.valueOf((new BigDecimal(rea)).setScale(pointLength - 1, 0).doubleValue());
                }
            }

            Double start1 = Double.valueOf(df.format(Double.valueOf(start)));
            if (pointLength > 1) {
                start1 = (new BigDecimal(start1)).setScale(pointLength - 1, 0).doubleValue();
            }

            Double end1 = Double.valueOf(end);
            int k = 0;

            int i;
            for(i = 0; i < 10; ++i) {
                StringBuffer buffer;
                if (start1 <= end1) {
                    if (df.format(start1).indexOf(46) == 0) {
                        buffer = new StringBuffer(df.format(start1));
                        buffer.insert(0, "0");
                        result[i] = buffer.toString();
                    } else {
                        result[i] = df.format(start1);
                    }

                    result[i] = result[i] + "-";
                    start1 = start1 + Double.valueOf(rea);
                    if (start1 <= end1 && i != 9) {
                        if (df.format(start1).indexOf(46) == 0) {
                            buffer = new StringBuffer(df.format(start1));
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
                        start1 = start1 + Double.valueOf(rea);
                    }

                    if (df.format(start1).indexOf(46) == 0) {
                        buffer = new StringBuffer(df.format(start1));
                        buffer.insert(0, "0");
                        result[i] = buffer.toString();
                    }

                    result[i] = df.format(start1) + "+";
                    ++k;
                }
            }

            for(i = 0; i < result.length; ++i) {
                int j = result[i].lastIndexOf("-");
                if (j != -1) {
                    result[i] = result[i].substring(0, j);
                }

                result[i] = result[i].replace("+", "");
            }

            return result;
        }
    }

    public static String startCal(String start) {
        if (Double.valueOf(start).equals(0.0D)) {
            return start;
        } else {
            while(start.charAt(start.length() - 1) == '0') {
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
}
