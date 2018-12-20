package hyman.service;

import hyman.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Component
public class ApiService {

    @Resource
    private UserService userService;

    //@APIMapping("")
    public User getUser(Integer id){
        Assert.notNull(id,"id 为空！");
        User user = new User("hello",20,1.1);
        return user;
    }

    public User returnUser(User user,Integer id){
        return user;
    }
}
