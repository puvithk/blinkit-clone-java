package user.service;

import user.dao.InMemoryUserDaoImpl;
import user.dao.UserDao;
import user.exceptions.UserNotFound;
import user.model.User;

public class UserService {
    // Declaring the User Dao object
    private final UserDao userDao = new InMemoryUserDaoImpl();

    public User getUserByPhone(String phone) {
        User user = userDao.findUserByPhone(phone);
        if(user== null){
            throw  new UserNotFound("User not found");
        }
        return user;
    }

    public User createUser(String phone) {
        User user = new User(phone);
        userDao.createUser(user);
        return user;
    }
}
