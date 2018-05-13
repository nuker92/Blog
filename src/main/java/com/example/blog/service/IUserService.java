package com.example.blog.service;


import com.example.blog.entity.User;

public interface IUserService {
    public User findUserByEmail(String email);

    public void saveUser(User user);

    public void updateUser(String email, User user);

    public void changeUserPassword(String email, String password);

    public User findUserByNick(String nick);

    public void toggleUserStatus(String nick);
}