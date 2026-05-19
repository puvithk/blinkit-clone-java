package user.service;

import user.dao.impl.InMemoryUserDaoImpl;
import user.dao.UserDao;
import user.exceptions.UserNotFound;
import user.model.User;

import java.util.List;

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

    public User getUserByUserId(Integer userId) {

        User user = userDao.findUserByUserId(userId);
        if(user== null){
            throw new UserNotFound("User not found");
        }
        return user;
    }

    public void updateUser(User user) {
        User currentUser = userDao.findUserByUserId(user.getId());
        if(currentUser == null){
            throw new UserNotFound("User not found");
        }
        userDao.updateUser(user , currentUser);

    }

    public List<User> getAllUser() {
        return userDao.findAllUser();
    }
}
