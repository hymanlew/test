package hyman.demo;

<<<<<<< HEAD
import hyman.entity.User;
import org.json.JSONArray;
import org.json.JSONObject;
=======
>>>>>>> a577d1ec65e51a687a6470d87c9351cccf139485
import org.springframework.scheduling.annotation.Scheduled;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

public class demo {

    public static void test(){
        try {
            String s = "123";
            File file = new File("testdata.xml");
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(s.getBytes());
            outputStream.flush();
            outputStream.close();
            System.out.println("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    public static void test1(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            System.out.println(jsonObject.getString("id"));

            // 这种方式只能接收 [] 数组类型的数据。
            //JSONArray jsonArray = new JSONArray(json);
            JSONArray jsonArray = jsonObject.getJSONArray("arrayName");
            System.out.println(jsonArray);

            for(int i=0;i<jsonArray.length();i++){
                JSONObject object = jsonArray.getJSONObject(i);
                String name = object.getString("name");
                Integer age = object.getInt("age");
                User user = new User(name,age,null);
                System.out.println(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //test();
        //System.out.println("helloword".equals("hello"));
        //System.out.println("helloword".indexOf("hello"));
        test1("{id:'hello'}");
=======


    public static void main(String[] args) {
        //test();

>>>>>>> a577d1ec65e51a687a6470d87c9351cccf139485
    }


}
