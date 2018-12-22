package hyman.dao;

import hyman.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    User login(String name, String age);

    List<List<Object>> usersCount(Map map);
}
