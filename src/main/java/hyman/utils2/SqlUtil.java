package hyman.utils2;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SqlUtil {
    /**
     * 仅支持字母、数字、下划线、空格、逗号（支持多个字段排序）
     */
    public static String SQL_PATTERN = "[a-zA-Z0-9_\\.\\-\\ \\,]+";

    /**
     * 检查字符，防止注入绕过
     */
    public static String escapeOrderBySql(String value) {
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value)) {
            return null;
        }
        if (StringUtils.isNotBlank(value)) {
            return Arrays.stream(value.split("-")).map(e -> e.replace(".ascend", " asc").replace(".descend", " desc"))
                    .collect(Collectors.joining(","));
        }
        return value;
    }

    /**
     * 验证 order by 语法是否符合规范
     */
    public static boolean isValidOrderBySql(String value) {
        return value.matches(SQL_PATTERN);
    }

    public static void main(String[] args) {
        System.out.println(escapeOrderBySql("name.ascend-sourcetype.ascend"));
    }
}

