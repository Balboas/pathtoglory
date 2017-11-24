package org.academiadecodigo.balboas.services;


/**
 * Created by codecadet on 07/11/17.
 */
public interface UserService extends Service{

    boolean authenticate(String pass, String user);

    boolean addUser(String username, String password, String email);

    boolean findByName(String username);

    long count();
}
