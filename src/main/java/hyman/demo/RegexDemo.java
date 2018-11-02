package hyman.demo;

import org.springframework.security.access.method.P;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * java.util.regex是一个用正则表达式所订制的模式来对字符串进行匹配工作的类库包。它包括两个类：Pattern和 Matcher 。
 * 还有一个 PatternSyntaxException，它是一个非强制异常类，它表示一个正则表达式模式中的语法错误。
 *
 * 一个 Pattern 是一个正则表达式经编译后的表现模式。 一个 Matcher 对象是一个状态机器，它依据Pattern对象做为匹配模式对字符串
 * 展开匹配检查。
 *
 * 首先一个 Pattern实例订制了一个所用语法与 PERL的类似的正则表达式经编译后的模式，然后一个 Matcher实例在这个给定的 Pattern实
 * 例的模式控制下进行字符串的匹配工作。
 */
public class RegexDemo {

    /**
     *
     规则	            正则表达式语法
     一个或多个汉字	    ^[\u0391-\uFFE5]+$
     邮政编码	        ^[1-9]\d{5}$
     QQ号码	            ^[1-9]\d{4,10}$
     邮箱	            ^[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\.){1,3}[a-zA-z\-]{1,}$
     用户名（字母开头 + 数字/字母/下划线）	^[A-Za-z][A-Za-z1-9_-]+$
     手机号码	        ^1[3|4|5|8][0-9]\d{8}$
     URL	            ^((http|https)://)?([\w-]+\.)+[\w-]+(/[\w-./?%&=]*)?$
     18位身份证号	    ^(\d{6})(18|19|20)?(\d{2})([01]\d)([0123]\d)(\d{3})(\d|X|x)?$
     *
     */
    public static void test1(){

        // Pattern 类型与 string 类型的匹配规则都是相同的，是有特定的格式，并且可以匹配到具体的字符。
        // . 表示任何字符（与行结束符可能匹配也可能不匹配），而 * 代表零次或多次。
        String content = "i am noob from runoob.com.";
        String pattern = ".*runoob.*";
        boolean patternMatch = Pattern.matches(pattern,content);
        System.out.println("patternMatch 是否包含了 'runoob' 子字符串? " + patternMatch);


        String num = "123";
        String nmatch = "\\d{3}";
        boolean n = num.matches(nmatch);
        System.out.println("匹配数字： " + n);

        String scontent = "abc";
        String smatch = "[a-zA-Z]*";
        boolean s = scontent.matches(smatch);
        System.out.println("匹配字母： " + s);
    }

    /**
     * 捕获组是把多个字符当一个单独单元进行处理的方法，它通过对括号内的字符分组来创建。
     * 捕获组是通过从左至右计算其开括号来编号。例如，在表达式（（A）（B（C））），有四个这样的组：
     *
     * ((A)(B(C))) ， (A) ， (B(C)) ， (C)
     *
     * 可以通过调用 matcher 对象的 groupCount 方法来查看表达式有多少个分组。groupCount 方法返回一个 int 值，表示 matcher
     * 对象当前有多个捕获组。还有一个特殊的组（group(0)），它总是代表整个表达式。该组不包括在 groupCount 的返回值中。
     *
     * 如果只是对字符的格式进行匹配的话，则下列代码与 string.matches(regex) 结果相同，但推荐使用下列方式。
     */
    public static void test2(){

        // {5} 是代表了需要匹配到的次数，从 0 开始算。
        String code = "320517";
        String regEx = "[1-9]\\d{5}";

        /**
         * 如果只是对字符的格式进行匹配的话，则下列代码与 string.matches(regex) 结果相同，但推荐使用下列方式。
         *
         * Pattern 类是正则表达式的编译表示形式，它指定为字符串的正则表达式必须首先被编译为此类的实例。然后可将得到的模式用
         * 于创建 Matcher 对象，依照正则表达式，该对象可以与任意字符序列匹配。执行匹配所涉及的所有状态都驻留在匹配器中，所以
         * 多个匹配器可以共享同一模式。
         *
         * 在仅使用一次正则表达式时，可以方便地通过此类定义 matches 方法，如 ：boolean b = Pattern.matches("a*b", "aaaaab");
         * 但它不允许重用已编译的模式，且匹配全部字符串。
         */

        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        Pattern pattern1 = Pattern.compile(regEx,Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(code);
        boolean result = matcher.matches();
        System.out.println("邮编匹配： "+result);


        String line = "hi num 3000? yes!";
        String spattern = "((\\D*)(\\d+)(.*))";
        pattern = Pattern.compile(spattern);
        matcher = pattern.matcher(line);

        int num = matcher.groupCount();
        System.out.println("表达式有多少个分组: "+num);

        // 如果是不带最外面的括号，则就只有 3个分组
        //String spattern = "(\\D*)(\\d+)(.*)";

        if(matcher.find()){
            // 当表达式含有多个分组时，则 group() = group(0) = group(1)，都是代表整个表达式。
            System.out.println("Found value: " + matcher.group() );
            System.out.println("Found value: " + matcher.group(0) );
            System.out.println("Found value: " + matcher.group(1) );
            System.out.println("Found value: " + matcher.group(2) );
            System.out.println("Found value: " + matcher.group(3) );
            System.out.println("Found value: " + matcher.group(4) );
        }else{
            System.out.println("NO MATCH");
        }

        System.out.println();

        // 返回以前匹配的索引位置（并不是字符所在的下标位置）
        System.out.println("matcher: " + matcher.start() );
        // 从给定的分组中捕获到子序列（匹配到的字符）的初始索引（类似 hashcode 值，不是顺序性的）
        System.out.println("matcher: " + matcher.start(3) );


        /**
         * replaceFirst 和 replaceAll 方法用来替换匹配正则表达式的文本。不同的是，replaceFirst 替换首次匹配，replaceAll
         * 替换所有匹配。
         *
         * Matcher 类也提供了appendReplacement 和 appendTail 方法用于文本替换：即替换后在原来的位置上追加字符，与 StringBuffer
         * 一起使用，目的就是为了在循环执行替换操作时，减少生成 string 对象。
         */
    }

    public static void test3(){

        // . 表示任何字符（与行结束符可能匹配也可能不匹配），而 * 代表零次或多次。
        String data = "hello hyman you are good!";
        String reg = ".*hyman.*";
        boolean result = Pattern.matches(reg,data);

        System.out.println("使用 pattern 模式： "+result);
        System.out.println("使用 string 模式： "+data.matches(reg));


        String data1 = "a hyman b";
        String reg1 = "[a-z]*\\s*hyman\\s*[a-z]*";
        System.out.println("使用 string 模式： "+data1.matches(reg1));

        Pattern pattern = Pattern.compile(reg1);
        Matcher matcher = pattern.matcher(data1);
        System.out.println("输出正则表达式： "+pattern.pattern());
        System.out.println("输出 matcher 操作的模式： "+matcher.pattern());


        String data2 = "ab11cd22ef33gh";
        String reg2 = "\\d+";
        pattern = Pattern.compile(reg2);
        String[] arr = pattern.split(data2);
        for(int i=0;i<arr.length;i++){
            System.out.println("pattern 拆分： "+arr[i]);
        }
    }

    public static void test4(){

        /**
         * matches 和 lookingAt 方法都用来尝试匹配一个输入序列模式。它们的不同是 matches 要求整个序列都匹配，而lookingAt
         * 不要求。 lookingAt 方法虽然不需要整句都匹配，但是需要从第一个字符开始匹配。
         * 这两个方法经常在输入字符串的开始使用。
         */
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher("11abc22");

        // 将从字符串开头开始的输入序列与该模式匹配。true
        System.out.println("lookingAt: " + matcher.lookingAt() );

        // find() 只检查是否可以匹配到字符串，不管它在什么位置
        System.out.println("find: " + matcher.find() );
        System.out.println("find by index: " + matcher.find(1) );
        System.out.println("find by index: " + matcher.find(3) );

        // 没有索引为 10 的已匹配到的字符串
        //System.out.println("find by index: " + matcher.find(10) );

        // 将整个字符串与模式匹配。false，只有为 true 时，start()才能正常执行
        //System.out.println("matches: " + matcher.matches() );

        // 只有当 matches(),lookingAt(),find() 都能成功执行匹配操作后，才可以使用以下的方法得到更详细的信息：
        // 更严格的来说，start() 只与 find() 的 true、false 相关，但前提是全字串匹配 matches() 必须为 true。
        // 如果 matches() 为 false，则必须把它放在 start() 下面。
        System.out.println("matcher: " + matcher.start() );

        // 没有分组为 3 的已匹配到的字符串
        //System.out.println("matcher: " + matcher.start(3) );
        System.out.println("matches: " + matcher.matches() );
    }

    public static void main(String[] args) {

        //test1();
        //test2();
        //test3();
        test4();
    }
}
