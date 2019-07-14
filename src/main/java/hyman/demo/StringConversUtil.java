package hyman.demo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;

public class StringConversUtil {

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
}
