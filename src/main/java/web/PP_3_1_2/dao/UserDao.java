package web.PP_3_1_2.dao;

import web.PP_3_1_2.model.User;

import java.util.List;

public interface UserDao {
    List<User> showAllUsers();

    void saveUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User showUser(int id);

}
