package hyman.demo;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by WeiJinTechnology on 2017/8/3.
 */

public class DateRange{


    private Date start;
    private Date end;
    private Long startL;
    private Long endL;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }


    public Long getStartL() {
        return startL;
    }

    public void setStartL(Long startL) {
        this.startL = startL;
    }

    public Long getEndL() {
        return endL;
    }

    public void setEndL(Long endL) {
        this.endL = endL;
    }

    public DateRange() {
    }

    public DateRange(Date start, Date end) {
        Calendar calendar=Calendar.getInstance();
        if(start!=null){
            // 使用给定的时间设置此 Date 对象
            calendar.setTime(start);
            // 重新设置为新的值，即 0时0分0秒
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);

            // 返回当前时间，以从历元至现在所经过的 UTC 毫秒数形式。
            this.startL=calendar.getTimeInMillis();
        }else{
            this.startL = null;
        }
        if(end!=null){
            calendar.setTime(end);

            // 为当前日期加一天，即 amount：1
            calendar.add(Calendar.DAY_OF_MONTH,1);
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);

            // 运行结果为 date.getTime() ，返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
            this.endL=calendar.getTime().getTime();
        }else{
            this.endL = null;
        }

    // 并且 calendar.getTime().getTime() == calendar.getTimeInMillis()，即数值相同
    }


}