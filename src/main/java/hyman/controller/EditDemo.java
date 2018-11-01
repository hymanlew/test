package hyman.controller;

import hyman.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("edit")
public class EditDemo {

    @RequestMapping("showEdit")
    public String showEdit(HttpServletRequest request){

        // 当一个很大的 double 数值从数据库提取被赋值到对象中时，它会自动转换为科学计算法，135E**（只有 double 类型）。
        // 这样显示到 jsp 页面的 input 标签中时，就会出来 135E**。这就需要转换
        Double wnum = 135E6;
        Integer lnum = 1350000000;
        User u = new User("test",lnum,wnum);
        request.setAttribute("lnum",u.getAge());
        request.setAttribute("wnum",u.getNum());

        /**
         * DecimalFormat 类是将数字进行格式化，主要靠 # 和 0 两种占位符号来指定数字长度。
         *
         * 特殊字符说明：
         *  “0” 指定位置不存在数字则显示为0  123.123 ->0000.0000 ->0123.1230
         *  "#" 指定位置不存在数字则不显示 123.123 -> ####.#### ->123.123
         *  "."  小数点
         *  "%" 会将结果数字乘以100 后面再加上% 123.123 ->#.00->1.3212%
         */
        String snum = new DecimalFormat("0.00").format(wnum);
        request.setAttribute("snum",snum);

        // 但当一个正常的数值被显示到 jsp 页面时，它会正常显示数字。
        long num = 1350000000000L;
        request.setAttribute("num",num);
        Double dnum = 1350000000000.0;
        request.setAttribute("dnum",dnum);
        return "editmodule";
    }

    @RequestMapping("introduce")
    public String introduce(){
        return "editIntroduce";
    }

    @RequestMapping("map")
    public String map(){
        return "map";
    }

    @RequestMapping("saveData")
    @ResponseBody
    public String saveData(String data){
        return data;
    }

    @RequestMapping("layui")
    public String layui(String data, HttpServletRequest request){
        return "layuido";
    }

    @RequestMapping("layuijs")
    public String layuijs(String data, HttpServletRequest request){
        request.getSession().setAttribute("test","测试中文乱码！");
        return "layuijs";
    }

    @RequestMapping("layuiframe")
    public String layuiframe(String data, HttpServletRequest request){
        return "iframe";
    }
}
