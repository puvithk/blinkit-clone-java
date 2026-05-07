package user.dao;

import user.model.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserDaoImpl implements UserDao {
    private List<User> users = new ArrayList<>(List.of());


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
        users.addLast(user);
    }
}
