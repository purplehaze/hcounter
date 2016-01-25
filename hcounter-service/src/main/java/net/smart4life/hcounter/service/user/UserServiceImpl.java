package net.smart4life.hcounter.service.user;

import net.smart4life.hcounter.dao.UserDAO;
import net.smart4life.hcounter.datamodel.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by roman on 21.09.2015.
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User findById(long id) {
        return userDAO.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    @Transactional
    public User insert(User user) {
        return userDAO.insert(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        return userDAO.update(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userDAO.delete(user);
    }
}
