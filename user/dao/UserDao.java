package user.dao;

import user.model.User;

public interface UserDao {
    User findUserByPhone(String phone);

    void createUser(User user);

    User findUserByUserId(Integer userId);
}
