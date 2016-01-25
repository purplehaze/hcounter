package net.smart4life.hcounter.datamodel.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by roman on 16.02.2015.
 */
@Entity
public class User extends BaseEntity {

    @NotNull
    @Size(max = 10, min = 2)
    @Column
    private String loginname;

    @Column
    private String password;

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
