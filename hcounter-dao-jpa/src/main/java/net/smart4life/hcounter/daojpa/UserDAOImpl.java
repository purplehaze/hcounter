package net.smart4life.hcounter.daojpa;

import net.smart4life.hcounter.dao.BaseDAO;
import net.smart4life.hcounter.dao.UserDAO;
import net.smart4life.hcounter.datamodel.entity.User;

import org.springframework.stereotype.Repository;

/**
 * Created by roman on 21.09.2015.
 */
@Repository
public class UserDAOImpl extends BaseDAOImpl<User> implements UserDAO {

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }
}
