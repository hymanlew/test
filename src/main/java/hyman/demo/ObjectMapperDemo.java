package hyman.demo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hyman.entity.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ObjectMapper 类是jackson提供的，是Jackson库的主要类，主要用来把对象转换成为一个json字符串返回到前端。
 * 它提供一些功能将转换成 Java 对象匹配 JSON 结构，反之亦然。它使用JsonParser和JsonGenerator的实例实现JSON实际的读/写。
 */
public class ObjectMapperDemo {

    public static ObjectMapper init() {
        ObjectMapper objectMapper = new ObjectMapper();

        /**
         * Include.ALWAYS       是序列化对像所有属性
         * Include.NON_DEFAULT  序列对象的所有属性
         * Include.NON_NULL     只有不为null的字段才被序列化
         * Include.NON_EMPTY    如果为 null 或者空字符串和空集合都不会被序列化
         */
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        //反序列化的时候如果多了其他属性,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //如果是空对象的时候,不抛异常，也就是对应的属性没有get方法
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        return objectMapper;
    }

    public static void demo(ObjectMapper objectMapper){

        try {
            User user = new User("lili",20,6.6);
            Date date = new Date();

            //这是最简单的一个例子,把一个对象转换为json字符串
            String personJson = objectMapper.writeValueAsString(user);
            personJson += objectMapper.writeValueAsString(date);
            System.out.println(personJson);

            //默认为true,会显示时间戳，即显示 long 毫秒数
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,true);
            personJson = objectMapper.writeValueAsString(user);
            personJson += objectMapper.writeValueAsString(date);
            System.out.println(personJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void demo1(ObjectMapper objectMapper){

        try {
            Date date = new Date();
            String dateStr = objectMapper.writeValueAsString(date);

            /**
             * 在使用 jackjson 解析字符串时，要特别注意，它会对对象进行完全匹配，即字符串只能使用双引号，而不能用单引。
             *
             * 下面的字符串在解析时，就会报解析异常：
             *  com.fasterxml.jackson.core.JsonParseException: Unexpected character (''' (code 39)): was expecting
             *  double-quote to start field name
             *
             *  但 fastjson好像没这个问题。
             */
            //String userStr = "{'name':'mimi','age':20,'date':'+dateStr+'}";

            String userStr = "{\"name\":\"mimi\",\"age\":20,\"date\":"+dateStr+"}";

            //这是最简单的一个例子,把json字符串转换为一个对象
            User user = objectMapper.readValue(userStr,User.class);
            System.out.println(user);

            //默认为true,如果多了其他属性，则会抛出异常，UnrecognizedPropertyException: Unrecognized field "date"
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,true);
            user = objectMapper.readValue(userStr,User.class);
            System.out.println(user);

            //同样的在序列化对象时，如果多了其他属性，也可以设置为是否抛出异常。
            //objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void demo2(ObjectMapper objectMapper){

        try {
            User user1 = new User("lili",20,60.7);
            User user2 = new User("mimi",22,70.6);

            List<User> users = new ArrayList<>();
            users.add(user1);
            users.add(user2);

            // 先转换为json字符串
            String userStr = objectMapper.writeValueAsString(users);
            System.out.println(userStr);

            /**
             * 在对集合性质的 json 数据进行反序列化时，都要指定为某种泛型，以便能准确地反序列化为对象。
             * 而指定反序列化的类型，就要使用 TypeReference，它可以明确的指定反序列化的类型，list、map 等等泛型。
             */
            // 然后反序列化为 List<user> 集合，需要通过 TypeReference 来具体传递值
            List<User> users2 = objectMapper.readValue(userStr,new TypeReference<List<User>>(){});
            System.out.println("=======1");
            for(User user : users2){
                System.out.println(user);
            }

            /**
             * 反序列化集合性质的 json 数据，有另外一种方法，但是此方法即将弃用，所以以后不要用。
             * JavaType 是所有实例类型类的基类，用于包含信息和作为反序列化器的键。其实例只能由TypeFactory构造。
             *
             * constructParametrizedType（泛型子类，泛型父类，参数类型)
             */
            JavaType javaType = objectMapper.getTypeFactory().constructParametrizedType(ArrayList.class,List.class,User.class);
            List<User> userList = objectMapper.readValue(userStr,javaType);
            System.out.println("=======2");
            for(User user : userList){
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void demo3(ObjectMapper objectMapper,Class<?> democlass){

        try {
            Class<User> userClass = User.class;
            User user = userClass.newInstance();

            Object object = democlass.newInstance();
            Field[] fields = democlass.getFields();
            Method[] methods = democlass.getMethods();

            /**
             * 创建对象的五种方式：
             *
             * 1.通过new 语句创建对象。
             *
             * 2.通过静态工厂方法创建，比如A.getNewInstance();
             *
             * 3.通过反射技术来实现。可以调用Class类或Constructor类的newInstance（）方法。
             *
             * 4.通过调用对象的clone（）方法。
             *
             * 5.利用I/O流技术的反序列化来实现。
             *
             *
             * 获取Class对象（实例）有三种方法：
             *
             * 1.通过class的方式创建Class实例：类名.class。
             *
             * 2.通过对象的getClass()方法获得。
             *
             * 3.通过Class类的forName()方法来获得。
             *
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ObjectMapper objectMapper = init();
        //demo(objectMapper);
        //demo1(objectMapper);
        demo2(objectMapper);
    }
}
