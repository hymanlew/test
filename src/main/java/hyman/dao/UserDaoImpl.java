package hyman.dao;

import hyman.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("userDao")
public class UserDaoImpl implements UserDao {
    @Override
    public User login(String name, String age) {
        return null;
    }

    @Override
    public List<List<Object>> usersCount(Map map) {
        //return resultmapper.selectAndCount(map);
        return null;
    }
}
