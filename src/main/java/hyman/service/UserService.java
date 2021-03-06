package hyman.service;

import hyman.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User login(String name, String age);

    List<List<Object>> usersCount(Map map);
}
