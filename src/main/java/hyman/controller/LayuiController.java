package hyman.controller;

import hyman.entity.LayuiUser;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("layui")
public class LayuiController {

    /**
     * layui table 接收的必须是 json 数据，或是 String 类型的 json 格式数据。
     *
     * 另外如果使用 map 或者 list 系统自带的对象进行传递，则按照普通的格式进行返回即可，但如果是返回 JSONObject 第三方对象
     * 或者是自定义的 object 集合对象类型（例如 ResultObj），那么必须要在返回数据时使用 produce（生产，创作）指定格式及编码，
     * 否则中文会乱码。
     *
     * 并且返回时必须要转成字符串，否则 layui 及任意 jsp 页面就无法正常接收，报 500。因为对象不能在页面之间传递。
     *
     * layui table 默认接受的数据格式：{ code: 0, msg: "", count: 1000,  data: [] }
     */
    @RequestMapping(value = "table",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUser(String name){

        System.out.println("====================="+name);
        List<LayuiUser> users = new ArrayList<>();
        users.add(new LayuiUser(1,"a","女","a city","aaaa"));
        users.add(new LayuiUser(2,"b","男","b city","aaaa"));
        users.add(new LayuiUser(3,"c","女","c city","中文"));

        JSONObject object = new JSONObject();
        object.put("code",0);
        object.put("msg","table 成功！");
        object.put("count",users.size());
        object.put("data",users);

        return object.toString();
    }

    // map 可以正常接收，并显示
    @RequestMapping("table1")
    @ResponseBody
    public Map<String,Object> getUser1(Integer page,Integer limit){

        List<LayuiUser> users = new ArrayList<>();

        for(int i=1;i<=limit;i++){
            if(i%2==0){
                users.add(new LayuiUser(i,"b","男","b city","aaaa"));
            }else {
                users.add(new LayuiUser(i,"c","女","c city","中文"));
            }
        }
        //users.add(new LayuiUser(1,"a","女","a city","aaaa"));
        //users.add(new LayuiUser(4,"c","女","c city","中文"));
        //users.add(new LayuiUser(5,"c","女","c city","中文"));
        //users.add(new LayuiUser(6,"c","女","c city","中文"));
        //users.add(new LayuiUser(7,"c","女","c city","中文"));
        //users.add(new LayuiUser(8,"c","女","c city","中文"));
        //users.add(new LayuiUser(9,"c","女","c city","中文"));
        //users.add(new LayuiUser(10,"c","女","c city","中文"));

        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","table1 成功！");
        map.put("count",users.size());
        map.put("data",users);
        return map;
    }

    // 自定义对象，但是非集合对象，可以正常接收并显示
    @RequestMapping("table2")
    @ResponseBody
    public LayuiUser getUser2(){
        return new LayuiUser(3,"c","女","c city","中文");
    }

    // list 可以正常接收，并显示
    @RequestMapping("table3")
    @ResponseBody
    public List<LayuiUser> getUser3(){

        List<LayuiUser> users = new ArrayList<>();
        users.add(new LayuiUser(1,"a","女","a city","aaaa"));
        users.add(new LayuiUser(2,"b","男","b city","aaaa"));
        users.add(new LayuiUser(3,"c","女","c city","中文"));
        return users;
    }

    /**
     * 如果是返回 JSONObject 第三方对象，或者是自定义的 object 集合对象类型（例如 ResultObj），那么就要在返回数据时指定格式及
     * 编码，否则中文会乱码。
     * 并且必须要转成字符串，否则 jsp 就无法接收，返回 JSONObject 报 500。
     */
    @RequestMapping("table4")
    @ResponseBody
    public String getUser4(String name){

        System.out.println("====================="+name);
        List<LayuiUser> users = new ArrayList<>();
        users.add(new LayuiUser(1,"a","女","a city","aaaa"));
        users.add(new LayuiUser(2,"b","男","b city","aaaa"));
        users.add(new LayuiUser(3,"c","女","c city","中文"));

        JSONObject object = new JSONObject();
        object.put("code",0);
        object.put("msg","");
        object.put("count",users.size());
        object.put("data",users);
        return object.toString();
    }
}
