package hyman.controller;

import hyman.entity.LayuiUser;
import hyman.service.UserService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("layui")
public class LayuiController {

    @Resource(name = "userService")
    private UserService userService;

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

    @RequestMapping("table5")
    @ResponseBody
    public String getUser5(String name){

        System.out.println("====================="+name);
        Map<String,Object> map = new HashMap<>();
        map.put("start",0);
        map.put("end",1);

        // 当使用 mybatis 的多 sql 查询（集合的多结果集）时，如果结果集是不同的类型，则必须以 object 类型接收，否则拿数据时报错。
        List<List<Object>> datas = userService.usersCount(map);
        return laypageData(datas).toString();
    }

    public JSONObject laypageData(List<List<Object>> datas){
        List<Object> users = datas.get(0);
        Integer count = Integer.parseInt(datas.get(1).get(0).toString());
        return tojson(users,count);
    }

    public JSONObject tojson(List<Object> datas,Integer count){
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",datas);
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject;
    }
}
