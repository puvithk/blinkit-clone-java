package user.dao.impl;

import mockData.MockData;
import user.dao.UserDao;
import user.model.User;

import java.util.List;

public class InMemoryUserDaoImpl implements UserDao {
    private final List<User> users = MockData.users;


    @Override
    public User findUserByPhone(String phone) {
        return users
                .stream()
                .filter(user -> user.getPhoneNumber().equals(phone))
                .findFirst()
                .orElse(null)
                ;}

    @Override
    public void createUser(User user) {
        int userId ;
        if(users.isEmpty()){
            userId = 0;
        }else {
            userId =  users.getLast().getId() + 1;
        }

        user.setId(userId);
        users.add(user);
        System.out.println(users.toString());
    }

    @Override
    public User findUserByUserId(Integer userId) {
        return users.stream()
                .filter(user -> user.getId()== userId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateUser(User user , User currentUser) {
        int index = users.indexOf(currentUser);
        users.set(index ,  user);

    }

    @Override
    public List<User> findAllUser() {
        return users;
    }


}
