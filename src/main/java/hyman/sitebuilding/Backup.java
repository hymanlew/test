package hyman.sitebuilding;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 本类专门存放智慧工地的测试，或者功能性的，一次性的代码。
 * 保存在这里，以备以后使用
 */
public class Backup {

    /**
     *
     http://localhost:8080/attendance/test

     @Resource
     private UserDao userService;

    @RequestMapping("test")
    public void test(){
        int[] sites = new int[3];

        String date = "2018-06-03";
        String date2 = "2018-06-20";

        for(int i=30;i<172;i++){
            if(i==143 || i==142 || i==134 ||i==133 ||i==109 ||i==108 ||i==94 ||i==92 ||i==84){
                continue;
            }else{
                AttendanceCount attendanceCount = attendanceCountService.findBySite(i,date);
                AttendanceCount attendanceCount1 = attendanceCountService.findBySite(i,date2);

                attendanceCount1.setLastRate(attendanceCount.getLastRate());
                attendanceCount1.setThreeRate(attendanceCount.getThreeRate());
                attendanceCountService.update(attendanceCount1);
            }

        }
    }
     */


}
