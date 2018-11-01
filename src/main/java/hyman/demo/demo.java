package hyman.demo;

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



    public static void main(String[] args) {
        //test();

    }


}
