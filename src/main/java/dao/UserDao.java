package dao;

import model.User;

import java.util.List;

public interface UserDao {

    User getOne(Long userId);
    List<User> getAll();
    User save(User user);
    void remove(Long userId);
}
