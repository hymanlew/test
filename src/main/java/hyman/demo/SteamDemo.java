package hyman.demo;

import com.sun.org.apache.xpath.internal.SourceTree;
import hyman.entity.User;
import sun.rmi.runtime.Log;

import javax.jws.soap.SOAPBinding;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream流是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列。即 Stream是依赖于某种数据源，数据源可以是数组、容器等，
 *        但不能是Map。但反过来从 Stream生成 Map是可以的。
 * Stream 的优点：声明性，可复合，可并行。这三个特性使得stream操作更简洁，更灵活，更高效。
 * Stream 的操作有两个特点：可以多个操作链接起来运行，内部迭代。
 *
 * 其组成元素是特定类型的对象，形成一个队列。 Java中的 Stream并不会存储元素，而是按需计算。
 * 数据源流的来源。可以是集合，数组，I/O channel， 产生器generator等。
 * 聚合操作类似SQL语句一样的操作，比如filter, map, reduce, find, match, sorted等。
 *
 *
 * Stream可分为并行流与串行流，它可以声明性地通过 parallel() 与 stream() 在并行流与顺序流之间进行切换。
 * 串行流就不必细说了，并行流主要是为了为了适应目前多核机器的时代，提高系统CPU、内存的利用率，并行流就是把一个内容分成多个数
 * 据块，并用不同的线程分别处理每个数据块的流。
 *
 * java1.8 并行流使用的是 fork/join 框架。
 *
 * 1、Stream不会自己存储数据。
 * 2、Stream不会改变原对象，他们会返回一个新的Stream。
 * 3、Stream操作是延迟的，他们会等到需要的结果时才执行。
 * 4、使用并行流并不一定会提高效率，因为 jvm 对数据进行切片和切换线程也是需要时间的。
 *
 *
 * 和以前的 Collection操作不同，Stream操作还有两个基础的特征：
 * - Pipelining：中间操作都会返回流对象本身。这样多个操作可以串联成一个管道，如同流式风格（fluent style）。 这样做可以对操作
 *   进行优化，比如延迟执行(laziness) 和 短路( short-circuiting)。
 *
 * - 内部迭代：以前对集合遍历都是通过 Iterator或者 For-Each的方式, 显式的在集合外部进行迭代，这叫做外部迭代。 Stream提供了内
 *   部迭代的方式，通过访问者模式(Visitor) 实现。
 */
public class SteamDemo {

    private static List<User> list = new ArrayList<>();

    public static void base(){
        list.add(new User("a",18,0.0));
        list.add(new User("b",25,0.0));
        list.add(new User("c",38,0.0));
        list.add(new User("d",38,0.0));
        list.add(new User("e",25,0.0));
    }

    public static void streamTest(){

        /**
         * 创建Stream，就是将一个数据源 （如：集合、数组）转化为一个流。
         *
         * 1、通过Collection系列提供的 stream()（串行） 或 parallelStream()（并行）获取数据流。
         * 2、通过Arrays中的静态方法 stream() 获取数据流。
         * 3、通过Stream类中的静态方法 of()获取数据流。
         */
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        // 串行流
        Stream<Integer> stream = list.stream();
        // 并行流
        Stream<Integer> stream1 = list.parallelStream();

        User[] users = new User[2];
        Stream<User> stream2 = Arrays.stream(users);

        // 不能使用 int[]， 否则流类型不匹配
        Integer[] arr = new Integer[3];
        Stream<Integer> stream3 = Arrays.stream(arr);

        Stream<String> stream4 = Stream.of("abc","cba");
    }

    /**
     * 中间操作，即对数据源进行一系列的操作处理。多个中间操作可以连接起来像一条流水线，除非流水线上触发器终止操作，否则中间
     * 操作不会执行任何的处理，而是在终止操作时一次性全部处理，成为惰性求值。
     */
    public static void test1(){

        /**
         * 筛选和切片：
         * 1、filter(predicate)，接收lambda，从流中排除某些元素。
         * 2、limit(n)-截断流，使其元素不超过给定数量。
         * 3、skip(n)-跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流，与limit(n)互补。
         * 4、distinct-筛选，通过流所生成元素的hashcode()和equals()去重复元素。
         */
        base();
        Stream<User> stream = list.stream();
        stream.filter((u)->u.getAge()<30)
                .distinct()
                .limit(2)
                .skip(1)
                .forEach(System.out::println);

        /**
         * 接口的静态方法和默认方法：
         *
         * Function是一个接口，那么 Function.identity() 的意思可以从两方面解释：
         *     1，Java 8允许在接口中加入具体方法。接口中的具体方法有两种，default方法和 static方法，identity()就是 Function
         *        接口的一个静态方法。
         *     2，Function.identity()返回一个输出跟输入一样的 Lambda表达式对象，等价于形如 t -> t形式的 Lambda表达式。
         *
         *
         * 方法引用：表达式 System.out::println 就是一个方法引用，这种语法用来替代某些特定形式 Lambda表达式，等价于Lambda表
         * 达式 x -> System.out.println(x)；
         * 如果Lambda表达式的全部内容就是调用一个已有的方法，那么可以用方法引用来替代Lambda表达式。
         *
         * 方法引用可以细分为四类：
         * (一)、object::instanceMethod（引用某个对象的方法）
         * (二)、Class::staticMethod（引用静态方法）
         *      这两种情况等价于提供方法参数的 Lambda表达式，如上述，System.out::println 等价于 x -> System.out.println(x)
         *
         * (三)、Class::instanceMethod（引用某个类的方法）   这种情况，第一个参数会成为方法的目标
         *
         * (四)、构造器引用（引用构造方法）：
         * 构造器引用与方法引用很类似，只不过方法名是new，例如：Person::new 是构造器引用。
         * 可以用数组类型建立构造器引用，例如：int[]::new, 它有一个参数：数组长度。等价于 x -> new int[x]
         */

    }

    public static void test2(){

        /**
         * 映射：
         * 1、map，接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，并对流中每一个元素应用函数，将其映射成一个新的元素。
         * 2、mapToDouble/mapToInt/mapToLong，接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的DoubleStream/IntStream/LongStream。
         * 3、flatMap，接收一个函数作为参数，将流中的每个值都换成一个流，然后把流连接成一个流。
         */
        base();

        // map，都是单个的流
        list.stream().map(User::getName)
                .forEach(System.out::println);

        list.stream().map(User::getName)
                .map(String::length)
                .forEach(System.out::println);

        list.stream().map(User::getName)
                .map(String::length)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        // flatmap，是单个的流一起合成的一个流
        List<List<User>> lists = new ArrayList<>();
        lists.add(list);
        lists.stream().flatMap(list -> getNames(list))
                .forEach(System.out::println);
    }

    public static Stream<String> getNames(List<User> list){

        List<String> list1 = new ArrayList<String>();
        for(User u : list){
            list1.add(u.getName());
        }
        return list1.stream();
    }

    public static Stream<Character> characterStream(String s){

        List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray()){
            result.add(c);
        }
        return result.stream();
    }

    public static void test3(){

        /**
         * 排序
         * 1、sorted()，产生一个新流，其中按自然顺序排序。
         * 2、sorted(Comparator)，产生一个新流，其中按比较器顺序排序。
         */
        List<String> list1 = Arrays.asList("aa","bb","cc","dd");

        // 默认 unecode 顺序排列
        list1.stream().sorted()
                        .forEach(System.out::println);

        // 倒序排列
        list1.stream().sorted((x,y) -> {
            if(x.equals(y)){
                return 1;
            }else {
                return -1;
            }
        }).forEach(System.out::println);


        // 按照 name unecode 顺序排列
        base();
        list.stream().sorted(Comparator.comparing(User::getName)).forEach(System.out::println);
    }

    public static void test4(){

        /**
         * 终止操作是执行中间操作链，并产生结果（一个新流）,数据源本身并不受影响，其结果可以是任何不是流的值。
         *
         * 查找与匹配：
         * 1、allMatch，检查是否匹配所有元素。
         * 2、anyMatch，检查是否至少匹配一个元素。
         * 3、noneMatch，检查是否所有的元素都没有匹配到。
         * 4、findFirst，返回第一个元素。
         * 5、findAny，返回当前流中的任意元素。
         * 6、count，返回流中元素的总数。
         * 7、max，返回流中最大值。
         * 8、min，返回流中最小值。
         * 9、froEach(Consumer c) 内部迭代。
         */
        base();
        boolean b = list.stream()
                .noneMatch((e) -> e.getName().equals("zhao"));
        System.out.println("noneMatch，是否所有的 name 都匹配不到 zhao: "+b);

        /**
         * Optional<T>类（java.util.Optional）是一个容器类，代表一个值存在或不存在。它是专门用于解决抛出 NPE 的异常情况的。
         * Optional 里面几种可以迫使你显式地检查值是否存在或处理值不存在的情形。
         *
         * isPresent()，将在Optional包含值的时候返回true, 否则返回false。
         * ifPresent(Consumer block)，会在值存在的时候执行给定的代码块。
         * T get()，会在值存在时返回值，否则抛出一个NoSuchElement异常。
         * T orElse(T other)，会在值存在时返回值，否则返回一个默认值。
         */
        Optional<User> optionalUser = list.parallelStream()
                .filter((x) -> x.getAge()==18)
                .findAny();
        System.out.println("查看 optional 容器的状态信息: "+optionalUser);

        // 判断是否有数据
        if(optionalUser.isPresent()){
            System.out.println("获取年龄是 18的任意一个用户: "+optionalUser.get());
            optionalUser.ifPresent(u -> System.out.println("获取该用户的年龄: "+u.getAge()));
        }else {
            System.out.println("没有年龄是 18的用户！ "+optionalUser.orElse(new User()));
            list.stream().filter((x) -> x.getAge()==25).forEach(System.out::println);
            list.stream().forEach(System.out::println);
        }

        optionalUser = list.parallelStream()
                .filter((x) -> x.getAge()==20)
                .findAny();
        // 判断是否有数据
        if(optionalUser.isPresent()){
            System.out.println("获取年龄是 20的任意一个用户: "+optionalUser.get());
        }else {
            System.out.println("没有年龄是 20的用户！");
            list.stream().filter((x) -> x.getAge()==25).forEach(System.out::println);

            System.out.println("查看所有的用户！");
            list.stream().forEach(System.out::println);
        }

        list.stream()
                .filter((u) -> u.getAge()==38)
                .findAny()
                .ifPresent(u -> System.out.println("获取该用户的名字: "+u.getName()));
    }

    public static void test5(){

        /**
         * 归约 reduce()，可以实现从一组元素中生成一个值，即可以将流中的值反复结合起来，得到一个值。
         * sum()、max()、min()、count()等都是 reduce操作。
         * 需要注意一旦执行完归约操作之后,当前流就被关闭了，不能重复利用。
         */
        // 找最大的值
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        //Optional<String> longest = stream.reduce((s1, s2) -> s1.length()>=s2.length() ? s1 : s2);
        //System.out.println(longest.get());
        Optional<String> longest = stream.max((s1,s2) -> s1.length()-s2.length());
        System.out.println(longest.get());


        stream = Stream.of("I", "love", "you", "too");
        // 求和                         初始值为 0,      累加器 sum 为和,       拼接器
        //Integer length = stream.reduce(0,(sum,str) -> sum+str.length(),(a,b) -> a+b);
        //System.out.println(length);
        Integer length = stream.mapToInt(str -> str.length()).sum();
        System.out.println(length);


        /**
         * reduce()擅长的是生成一个值，如果想要从 Stream生成一个集合或者Map等复杂的对象，那就需要使用收集器 collect()！
         *
         * 收集器（Collector）是为Stream.collect()方法量身打造的工具接口（类）。即是将一个 Stream转换成一个容器（或者Map），
         * 然后进行元素的增加，规约，删除等等操作。
         * 而 Collectors工具类就是通过其静态方法生成各种常用的 Collector，以用于各种情况下的操作。
         *
         */
        base();
        list.stream()
                .map(User::getName)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void test6(){

        base();
        //转HashSet
        HashSet<String> set = list.stream()
                .map(User::getName)
                .collect(Collectors.toCollection(HashSet::new));
        set.forEach(System.out::println);

        //总数，只能以 long 类型接收
        long count = list.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        //平均年龄
        Collectors.averagingInt(User::getAge);

        //总年龄
        Collectors.summingInt(User::getAge);

        //最大值
        Optional<User> u = list.stream()
                .collect(Collectors.maxBy((e1,e2)
                        -> Integer.compare(e1.getAge(),e2.getAge() )));
        System.out.println(u);

        //平均年龄
        IntSummaryStatistics collect = list.stream()
                .collect(Collectors.summarizingInt(User::getAge));
        System.out.println(collect.getAverage());

        //分组
        Map<Integer, List<User>> l= list.stream()
                .collect(Collectors.groupingBy(User::getAge));
        System.out.println(l);

        //多级分组
        Map<Integer,Map<String,List<User>> > ls= list.stream()
                .collect(Collectors.groupingBy(
                        User::getAge,Collectors.groupingBy(User::getName)));
        System.out.println(ls);

        //分区
        Map<Boolean,List<User>> map= list.stream()
                .collect(Collectors.partitioningBy((x)
                        -> x.getAge()>18));
        System.out.println(map);

        //连接字符串
        String str = list.stream().map(User::getName)
                .collect(Collectors.joining(",","-","-"));
        System.out.println(str);

    }

    public static void main(String[] args) {

        //test1();
        //test2();
        //test3();
        //test4();
        test5();
    }
}
