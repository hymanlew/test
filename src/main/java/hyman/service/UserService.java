package hyman.service;

import hyman.entity.User;

public interface UserService {
    User login(String name, String age);
}
