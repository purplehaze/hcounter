package net.smart4life.hcounter.service.user;

import java.util.List;

import net.smart4life.hcounter.datamodel.entity.User;

/**
 * Created by roman on 21.09.2015.
 */
public interface UserService {

    User findById(long id);

    List<User> findAll();

    User insert(User user);

    User update(User user);

    void delete(User user);
}
