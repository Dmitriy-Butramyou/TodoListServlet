package dao;

import model.User;

import java.util.List;

public interface UserDao {

    User getOne(Long taskId);
    List<User> getAll();
    User save(User user);
}
