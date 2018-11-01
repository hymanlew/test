package hyman.demo;

public class InserAttendance {

    /**
     *
     SELECT * FROM zh_site_record WHERE employee_id in (13564,
     13565,
     13566,
     13567,
     13568,
     13569,
     14207,
     14208,
     14209,
     14210)

     修改 leaving time，op time 为 2018-07-17 19:09:50；


     INSERT INTO zh_site_record set employee_id=13564,site_id=265,time='2018-08-2 11:09:50';
     INSERT INTO zh_site_record set employee_id=13565,site_id=265,time='2018-08-2 11:09:50';
     INSERT INTO zh_site_record set employee_id=13566,site_id=265,time='2018-08-2 11:09:50';
     INSERT INTO zh_site_record set employee_id=13567,site_id=265,time='2018-08-2 11:09:50';
     INSERT INTO zh_site_record set employee_id=13568,site_id=265,time='2018-08-2 11:09:50';
     INSERT INTO zh_site_record set employee_id=13569,site_id=265,time='2018-08-2 11:09:50';
     INSERT INTO zh_site_record set employee_id=14207,site_id=265,time='2018-08-2 11:09:50';
     INSERT INTO zh_site_record set employee_id=14208,site_id=265,time='2018-08-2 11:09:50';
     INSERT INTO zh_site_record set employee_id=14209,site_id=265,time='2018-08-2 11:09:50';
     INSERT INTO zh_site_record set employee_id=14210,site_id=265,time='2018-08-2 11:09:50';

     先不要加离场时间，到 10 号再加上 （,leaving='2018-09-10 18:09:50',operate_time='2018-09-10 19:29:50';）

     然后再 select 检查一遍
     SELECT * FROM zh_site_record WHERE employee_id in (13564,
     13565,
     13566,
     13567,
     13568,
     13569,
     14207,
     14208,
     14209,
     14210) AND site_id=265

     SELECT * FROM zh_gate WHERE site_id=265（检查闸机）
     id	num	name	site					type
     48975	S13200	考勤机	265	1	2018-08-02 10:09:05	2		0


     SELECT * FROM zh_card WHERE employee_id in (13564,
     13565,
     13566,
     13567,
     13568,
     13569,
     14207,
     14208,
     14209,
     14210)

     将 state 全部修改为 1，作废原卡。


     INSERT INTO zh_card SET num='A0ECE98B',employee_id=13564,site_id=265,state=0,create_time='2018-08-02 11:10:50';
     INSERT INTO zh_card SET num='E53DC26F',employee_id=13565,site_id=265,state=0,create_time='2018-08-02 11:10:50';
     INSERT INTO zh_card SET num='B6AFE889',employee_id=13566,site_id=265,state=0,create_time='2018-08-02 11:10:50';
     INSERT INTO zh_card SET num='BX7BAA82',employee_id=13567,site_id=265,state=0,create_time='2018-08-02 11:10:50';
     INSERT INTO zh_card SET num='05C71AD9',employee_id=13568,site_id=265,state=0,create_time='2018-08-02 11:10:50';
     INSERT INTO zh_card SET num='5C3EE985',employee_id=13569,site_id=265,state=0,create_time='2018-08-02 11:10:50';
     INSERT INTO zh_card SET num='37F16F15',employee_id=14207,site_id=265,state=0,create_time='2018-08-02 11:10:50';
     INSERT INTO zh_card SET num='556BA521',employee_id=14208,site_id=265,state=0,create_time='2018-08-02 11:10:50';
     INSERT INTO zh_card SET num='352CD815',employee_id=14209,site_id=265,state=0,create_time='2018-08-02 11:10:50';
     INSERT INTO zh_card SET num='F3B6EF59',employee_id=14210,site_id=265,state=0,create_time='2018-08-02 11:10:50';

     然后再 select 检查一遍
     SELECT * FROM zh_card WHERE employee_id in (13564,
     13565,
     13566,
     13567,
     13568,
     13569,
     14207,
     14208,
     14209,
     14210) AND state=0

     插入考勤数据：
     http://localhost:8080/finish/insertAttendance

     */



    /**
     *
     @Resource
     public AttendanceService attendanceService;

     @RequestMapping("insertAttendance")
     public void test1(){

     List<String> cardids = new ArrayList<>();
     cardids.add("A0ECE98B");
     cardids.add("E53DC26F");
     cardids.add("B6AFE889");

     cardids.add("BX7BAA82");
     cardids.add("05C71AD9");
     cardids.add("5C3EE985");

     cardids.add("37F16F15");
     cardids.add("556BA521");
     cardids.add("352CD815");
     cardids.add("F3B6EF59");

     Long uuid = 3215956129208310L;
     String gateId = "S13200";
     int day = 3;

     System.out.println("**********  八月 **********");
     while (day<32){
     Calendar calendar = Calendar.getInstance();
     calendar.set(2018,7,day++);

     System.out.println("======= 上班 ========"+calendar.getTime());
     for(String card : cardids){
     Random random = new Random();
     Integer mini = random.nextInt(28);

     calendar.set(Calendar.HOUR_OF_DAY,6);
     calendar.set(Calendar.MINUTE,mini+30);
     calendar.set(Calendar.SECOND,mini);
     Date date = calendar.getTime();
     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String d = format.format(date);

     attendanceService.testattdence(card, (uuid++).toString(), gateId, 0, d);
     System.out.println(card+"==="+uuid+"==="+gateId+"==="+0+"==="+d);
     }

     System.out.println("======= 下班 ========"+calendar.getTime());
     for(String card : cardids){
     Random random = new Random();
     Integer mini = random.nextInt(28);

     calendar.set(Calendar.HOUR_OF_DAY,17);
     calendar.set(Calendar.MINUTE,mini+30);
     calendar.set(Calendar.SECOND,mini);
     Date date = calendar.getTime();
     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String d = format.format(date);

     attendanceService.testattdence(card, (uuid++).toString(), gateId, 1, d);
     System.out.println(card+"==="+uuid+"==="+gateId+"==="+1+"==="+d);
     }
     }


     System.out.println("**********  九月 **********");
     day = 1;
     while (day<6){
     Calendar calendar = Calendar.getInstance();
     calendar.set(2018,8,day++);

     System.out.println("======= 上班 ========"+calendar.getTime());
     for(String card : cardids){
     Random random = new Random();
     Integer mini = random.nextInt(28);

     calendar.set(Calendar.HOUR_OF_DAY,6);
     calendar.set(Calendar.MINUTE,mini+30);
     calendar.set(Calendar.SECOND,mini);
     Date date = calendar.getTime();
     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String d = format.format(date);

     attendanceService.testattdence(card, (uuid++).toString(), gateId, 0, d);
     System.out.println(card+"==="+uuid+"==="+gateId+"==="+0+"==="+d);
     }

     System.out.println("======= 下班 ========"+calendar.getTime());
     for(String card : cardids){
     Random random = new Random();
     Integer mini = random.nextInt(28);

     calendar.set(Calendar.HOUR_OF_DAY,17);
     calendar.set(Calendar.MINUTE,mini+30);
     calendar.set(Calendar.SECOND,mini);
     Date date = calendar.getTime();
     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String d = format.format(date);

     attendanceService.testattdence(card, (uuid++).toString(), gateId, 1, d);
     System.out.println(card+"==="+uuid+"==="+gateId+"==="+1+"==="+d);
     }
     }
     //attendanceService.testattdence(card, uuid, gatenum, Direction, time);
     }



     public void testattdence(String cardId,String uuid,String gatenum,Integer onoff,String time);



     @Override
    public void testattdence(String ICCardID, String Guid, String ZjID, Integer Direction, String timeStr) {

    Attendance attendance=new Attendance();
    String cardId="";
    Date time=null;
    cardId=ICCardID;
    time=DateUtil.StrToDateWithPattern(timeStr,"yyyy-MM-dd HH:mm:ss");

    Card card=cardDao.getByNum(cardId);//根据IC卡卡号拿到卡
    Employee employee=null;
    if(card==null){

    }else{
    if(card.getState()!=0){//状态不为0，则已失效
    throw new RuntimeException("考勤卡已失效");
    }
    if(card.getEmployeeId()==null){
    return;
    }
    attendance.setCardId(card.getId());
    attendance.setEmployeeId(card.getEmployeeId());
    employee=employeeDao.getIdentityInfo(card.getEmployeeId());
    }
    Gate gate=gateDao.getByNum(ZjID.trim());//根据号码拿到闸机

    attendance.setGid(Guid);
    attendance.setGateId(gate.getId());
    attendance.setAddress(gate.getSite().getName());
    attendance.setSiteId(gate.getSiteId());
    attendance.setTime(time);
    attendance.setDate(time);
    attendance.setDateTime(time);
    attendance.setType(Direction);
    attendanceDao.save(attendance);
    }
     */
}
