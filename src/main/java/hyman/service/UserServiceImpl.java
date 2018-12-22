package hyman.service;

import hyman.dao.UserDao;
import hyman.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component("userService")
public class UserServiceImpl implements UserService{

    @Resource(name = "userDao")
    private UserDao userDao;

    @Override
    public User login(String name, String age) {
        return null;
    }

    @Override
    public List<List<Object>> usersCount(Map map) {
        return userDao.usersCount(map);
    }
}
