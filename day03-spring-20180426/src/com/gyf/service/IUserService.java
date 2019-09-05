package com.gyf.service;

import com.gyf.model.User;

public interface IUserService {

    public void add();

    public void add(User user);

    //切面编程
    public void addUser();
    public void updateUser();
    public void deleteUser();
    public int deleteUser(int id);
}
