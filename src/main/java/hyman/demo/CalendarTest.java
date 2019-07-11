package hyman.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
    public static void main(String[] args) throws ParseException {

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
}
