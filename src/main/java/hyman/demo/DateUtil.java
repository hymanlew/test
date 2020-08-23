/**
 * Copyright (C) 2000-2006 
Reserved.
 */
package hyman.demo;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class Function Description
 *
 * @author SYSTEM
 * @Date 2015年1月21日
 */
public class DateUtil {

	/**
	 * 把字符串转为固定时间格式的字符串(xx:xx)
	 * @param s
	 * @return
	 * @author SYSTEM
	 * Date 2015年1月21日
	 * @version
	 */
	public static String fmString(String s){
		Date dDate;
		String reTime = "";
		try {
			dDate = new SimpleDateFormat("HH:mm").parse(s);
			reTime = new SimpleDateFormat("HH:mm").format(dDate);
		} catch (ParseException e) {
			reTime = "00:00";
		}
		return reTime;
	}


	public static Date dateFormatDate(Date date){
		Date fDate = new Date();
		String reDate="";
		reDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		try {
			fDate = new SimpleDateFormat("yyyy-MM-dd").parse(reDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return fDate;
	}


	/**
	 * 把Date转为固定时间格式的字符串(yyyy-MM-dd hh:mm)
	 * @return
	 * @author SYSTEM
	 * Date 2015年1月21日
	 * @version
	 */
	public static String fmYYS(Date d){
		String reTime = "";
		if(d!=null){
			reTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(d);
		}else{
			reTime="1970-01-01 00:00";
		}
		return reTime;
	}

	public static String fmPattern(Date d,String pattern){
		String reTime = "";
		if(d!=null){
			reTime = new SimpleDateFormat(pattern).format(d);
		}else{
			reTime="1970-01-01 00:00";
		}
		return reTime;
	}

	public static Date StrToDate(String str) {
		SimpleDateFormat format;
		if(str==null||str.length()==0){
			return null;
		}
		if(str.contains(":")){
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    		  
		}else {
			format = new SimpleDateFormat("yyyy-MM-dd"); 
		}
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date StrToDateWithPattern(String str,String pattern) {

		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取上个月的第一天/最后一天
	 * @param date
	 * @return
	 */
	public static Map<String, String> getFirstday_Lastday_Month(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		Date theDate = calendar.getTime();

		//上个月第一天
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
		day_first = str.toString();

		//上个月最后一天
		calendar.add(Calendar.MONTH, 1);    //加一个月
		calendar.set(Calendar.DATE, 1);        //设置为该月第一天
		calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
		String day_last = df.format(calendar.getTime());
		StringBuffer endStr = new StringBuffer().append(day_last).append(" 23:59:59");
		day_last = endStr.toString();

		Map<String, String> map = new HashMap<String, String>();
		map.put("first", day_first);
		map.put("last", day_last);
		return map;
	}

	/**
	 * 当月第一天
	 * @return
	 */
	public static String getFirstDay() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();

		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
		return str.toString();

	}
	
    /**
     * 当月最后一天
     * @return
     */
	public static String getLastDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        String s = df.format(theDate);
        StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
        return str.toString();

    }

	// 比较考勤时间 date，与登记的入职离职时间 rangelist
    public static boolean inRange(List<DateRange> rangeList,Date date){
		boolean flag=false;
		if(date==null) {
			return flag;
		}

		// 如果工地流转记录时间不为空，有时
		if(rangeList!=null&&rangeList.size()>0){

			// 比较每个工地中的登记的时间，与当前考勤时间
			for(DateRange range:rangeList){
				if(inRange(range,date)){
					return true;
				}
			}
		}
		return flag;
	};

	// 比较每个考勤时间 date，与每个工地中的登记的时间 range
	public static boolean inRange(DateRange range,Date date){
		boolean flag=false;
		if(date==null)
			return flag;
		long time=date.getTime();

		// 开始比较每个工地中的登记的开始及结束时间 time，leavingtime
		// 如果没有离职时间，但有入职时间时，比对当前考勤时间与入职时间
		if(range.getEndL()==null&&range.getStartL()!=null){
			if(time>=range.getStartL()){
				return true;
			}else{
				return false;
			}
		}else

		//	如果有离职时间
		if(range.getEndL()!=null&&range.getStartL()!=null){
			if(time>=range.getStartL()&&time<range.getEndL()){
				return true;
			}else{
				return false;
			}
		}else
		if(range.getEndL()!=null&&range.getStartL()==null){
			if(time<range.getEndL()){
				return true;
			}else{
				return false;
			}
		}else
		if(range.getEndL()==null&&range.getStartL()==null){
			return false;
		}

		return flag;

	};
	public static Date double2Date(Double d)
	{
		Date t=null;
		try   {
			Calendar base = Calendar.getInstance();
			SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//Delphi的日期类型从1899-12-30开始
			base.set(1899, 11, 30, 0, 0, 0);
			base.add(Calendar.DATE, d.intValue());
			base.add(Calendar.MILLISECOND,(int)((d % 1) * 24 * 60 * 60 * 1000));
			t=base.getTime();
		}
		catch   (Exception   e)   {
			e.printStackTrace();
		}
		return t;
	}

	public static String setNo(){
		String no = new SimpleDateFormat("yyMMddHHmm").format(new Date());
		return no;
	}


	/**
	 * ISO时间格式转换
	 */
	public static String ISO2Java(String time) throws ParseException {
		if (StringUtils.isEmpty(time)) {
			return time;
		}

		// 移除毫秒
		time = time.replaceAll("\\.[\\d]*$", "");

		// 如果日期格式已转移，直接返回
		if (time.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
			return time;
		}

		// 转换数据格式
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date date = formatter.parse(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 转换日期为long
	 */
	public static Long getMilliseconds(String time) throws ParseException {
		if (StringUtils.isEmpty(time)) {
			return System.nanoTime();
		}

		// 如果日期格式已转移，直接返回
		Date date;
		if (time.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}.\\d{1,3}")) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			date = formatter.parse(time);
		} else if(time.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = formatter.parse(time);
		} else {
			// 转换数据格式
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			date = formatter.parse(time);
		}
		return date.getTime();
	}

	public static void main(String [] args ){

		System.out.println(double2Date(43028.6468171296));
		System.out.println(setNo());
	}
}
