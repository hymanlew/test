package hyman.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

    public static void main(String[] args) throws ParseException {

        //testSubList();
        //System.out.println(Math.ceil(1));
        //rand();
        listRemove();
    }

    public static void testDate() throws ParseException {

        String time = "2018-01-05 10:10:0";
        String pattern = "yyyy-MM-dd HH:mm:ss";

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = format.parse(time);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 返回当前时间，以从历元至现在所经过的 UTC 毫秒数形式。
        long t = calendar.getTimeInMillis();

        // 运行结果为 date.getTime() ，返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
        long t1 = calendar.getTime().getTime();

        // 结果为 true，即值相同
        System.out.println(t==t1);

        System.out.println(calendar.getTime());
        System.out.println(date);

        // 为当前日期加一天，即 amount：1
        calendar.add(Calendar.DAY_OF_MONTH,1);
        date = calendar.getTime();
        System.out.println(time);
        System.out.println(format.format(date));
    }

    public static void testSubList(){

        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list);

        //截取时含头不含尾
        List<Integer> slist = list.subList(1,3);
        System.out.println(slist.toString());

        List<Integer> slist1 = list.subList(1,list.size());
        System.out.println(slist1.toString());

        /**
         *  [1, 2, 3, 4, 5]
         *  [2, 3]
         *  [2, 3, 4, 5]
         */
    }

    public static void rand(){
        Random random = new Random();
        for(int i=0;i<100;i++){
            int num = random.nextInt(100);
            System.out.println(num);
        }
    }

    public static void listRemove(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

        /**
         * JAVA中循环删除list中元素的方法总结：
         * JAVA中循环遍历list有三种方式for循环、增强for循环（也就是常说的foreach循环）、iterator遍历。
         */


        /**
         * 增强for循环：此种方式删除元素会抛出修改异常：
         * Exception in thread "main" java.util.ConcurrentModificationException
         * 因为 list 在内部实现修改方法时，是使用上一元素和下一元素的下标，两个值的对比来进行操作的。而直接使用其内部的
         * remove 方法，则会使其下标数量不一致，元素在使用的时候发生了并发的修改，从而抛出修改异常。
         * 但是删除完毕马上使用break跳出，则不会触发报错。
         */
        //for(String s : list){
        //    if("b".equals(s)){
        //        list.remove(s);
        //    }
        //}


        /**
         * for 循环遍历list：
         *
         * 这种方式的问题在于，删除某个元素后，list的大小发生了变化，而你的索引也在变化，所以会导致你在遍历的时候漏掉某些元素。
         * 比如当你删除第1个元素后，继续根据索引访问第2个元素时，因为删除的关系后面的元素都往前移动了一位，所以实际访问的是第3
         * 个元素。因此，这种方式可以用在删除特定的一个元素时使用，但不适合循环删除多个元素时使用。
         */
        //for(int i=0;i<list.size();i++){
        //    if(list.get(i).equals("b")){
        //        list.remove(i);
        //    }
        //}
        //System.out.println(list);

        /**
         * iterator遍历：
         *
         * 这种方式可以正常的循环及删除。但要注意的是必须使用 iterator 的 remove 方法，如果用 list 的 remove 方法同样会报
         * 上面提到的 ConcurrentModificationException 错误。
         */
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String s = iterator.next();
            if("b".equals(s)){
                iterator.remove();
            }
        }
        System.out.println(list);

        /**
         * 总结：
         *  （1）循环删除list中特定一个元素的，可以使用三种方式中的任意一种，但在使用中要注意上面分析的各个问题。
         *  （2）循环删除list中多个元素的，应该使用迭代器iterator方式。
         */
    }

}
