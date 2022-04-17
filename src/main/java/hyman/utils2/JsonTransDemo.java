package hyman.utils2;

import com.google.gson.Gson;
import hyman.entity.JsonEntity;
import hyman.entity.User;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonTransDemo {

    //{"num":"1","name":"lili","type":"10","users":[{"name":"lala","age":25}]}

    public static void test(String json){
        Gson gson = GsonUtil.getGson();
        JsonEntity entity = gson.fromJson(json,JsonEntity.class);
        String name = entity.getName();
        User user = entity.getUsers().get(0);
        System.out.println(name);
        System.out.println(user);
    }

    public static void test1(String json){
        JSONObject object = new JSONObject(json);
        String name = object.getString("name");

        JSONArray array = object.getJSONArray("users");
        for(int i=0;i<array.length();i++){

            JSONObject obj = array.getJSONObject(i);
            String uname = obj.getString("name");
            Integer age = obj.getInt("age");
            User user = new User(uname,age,null);

            // {"name":"lala","age":25}
            System.out.println(obj);
            System.out.println(user);
        }
        System.out.println(name);

        // [{"name":"lala","age":25}]
        System.out.println(array);
    }

    public static void main(String[] args) {
        String json = "{\"num\":\"1\",\"name\":\"lili\",\"type\":\"10\",\"users\":[{\"name\":\"lala\",\"age\":25}]}";
        //test(json);
        test1(json);
    }
}
