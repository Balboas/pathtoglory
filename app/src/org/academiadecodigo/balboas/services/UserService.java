package org.academiadecodigo.balboas.services;


import org.academiadecodigo.balboas.model.Client;
import org.academiadecodigo.balboas.model.User;

/**
 * Created by codecadet on 07/11/17.
 */
public interface UserService extends Service{

    boolean authenticate(String pass, String user);

    void addUser(User user);

    User findByName(String username);

    long count();
}
