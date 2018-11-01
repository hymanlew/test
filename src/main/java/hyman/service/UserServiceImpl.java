package hyman.service;

import hyman.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("userService")
public class UserServiceImpl implements UserService{
    @Override
    public User login(String name, String age) {
        return null;
    }
}
