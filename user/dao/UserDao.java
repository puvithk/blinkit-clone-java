package user.dao;

import user.model.User;

import java.util.List;

public interface UserDao {
    User findUserByPhone(String phone);

    void createUser(User user);

    User findUserByUserId(Integer userId);


    void updateUser(User user , User currentUser);

    List<User> findAllUser();
}
